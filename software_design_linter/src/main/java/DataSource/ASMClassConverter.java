package DataSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.ParameterNode;
import org.objectweb.asm.tree.VarInsnNode;

public class ASMClassConverter implements ClassConverter {
    ArrayList<String> seenClasses = new ArrayList<String>();
    ArrayList<String> classesTo = new ArrayList<String>();
    HashMap<String, ClassObject> allclasses = new HashMap<String, ClassObject>();
    List<String> exclusionList = Arrays.asList("<init>","<clinit>");

    @Override
    public HashMap<String, ClassObject> convertAll(String javaClassName) {
        this.seenClasses = new ArrayList<String>();
        this.allclasses = new HashMap<String, ClassObject>();
        this.seenClasses.add(javaClassName);
        ClassObject clob = convertOneClass(javaClassName);
        this.allclasses.put(javaClassName, clob);
        return this.allclasses;
    }
    //Converts One Class to a ClassObject based on string
    @Override
    public ClassObject convertOneClass(String javaClassName) {
        try{
            //Initialize Reader
            ClassReader reader = new ClassReader(javaClassName);
            ClassNode classNode = new ClassNode();
            reader.accept(classNode, ClassReader.EXPAND_FRAMES);

            //Get Name, Access,  & ExtendedClass
            String name = classNode.name;
            AccessModifier access = getAccess(classNode.access);
            String signature = classNode.signature;
            String niceName = Type.getObjectType(classNode.name).getClassName();
            ClassType type = getClassType(classNode);
            String extendedClass = classNode.superName;

            // classListAdd(extendedClass);

            //get ImplementedClasses
            ArrayList<String> implementedClasses = getImplementedClasses(classNode.interfaces);
            //get field objects
            ArrayList<FieldObject> fields = getFields(classNode.fields);
            //get methods
            ArrayList<MethodObject> methods = getMethods(classNode.methods);
            //return ClassObject
            ClassObject clob = new ClassObject(name, access, signature, niceName, type, implementedClasses, extendedClass, fields, methods);
            return clob;
        } catch (IOException e) {
            return null;
        }
    }
    // logic beind adding classes
    private void classListAdd(String className) {
        if(!this.seenClasses.contains(className) && !this.allclasses.keySet().contains(className) && className != null) {
            if(!exclusionList.contains(className) && !className.contains("java") && !className.equals("V")){
                //System.out.println(className);
                this.seenClasses.add(className);
                ClassObject clob = convertOneClass(className);
                if(clob != null){
                    this.allclasses.putIfAbsent(clob.niceName, clob);
                }
               
            }
        }
    }
    //Determines wether a classnode is abstract or interface
    private ClassType getClassType(ClassNode classNode) {
        if ((classNode.access & Opcodes.ACC_INTERFACE) != 0){
            return ClassType.INTERFACE;
        } else if ((classNode.access & Opcodes.ACC_ABSTRACT) != 0){
            return ClassType.ABSTRACT;
        }
        return ClassType.CLASS;
    }
    //returns an arraylist of methodobjects from a class node
    private ArrayList<MethodObject> getMethods(List<MethodNode> methods){
        ArrayList<MethodObject> methodsOB = new ArrayList<>();
        if(methods == null){
            return methodsOB;
        }
        for(MethodNode m : methods) {
            String mame = m.name;
            AccessModifier maccess = getAccess(m.access);
            String msig = m.signature;
            String mreturntype = Type.getReturnType(m.desc).toString();
            classListAdd(mreturntype);
            ArrayList<String> mexceptions = new ArrayList<String>();
            for(String e : m.exceptions) {
                mexceptions.add(e);
            }
            ArrayList<FieldObject> mlocalfields = getLocalField(m.localVariables);
            ArrayList<FieldObject> mfields = getArguments(Type.getArgumentTypes(m.desc), m.parameters);
            ArrayList<InstructionObject> minst = getInstructions(m.instructions);
            methodsOB.add(new MethodObject(mame, maccess, msig, cleanUp(mreturntype), mexceptions, mfields,mlocalfields, minst));
        }
        return methodsOB;
    }
    private ArrayList<FieldObject> getLocalField(List<LocalVariableNode> localVariables) {
        ArrayList<FieldObject> localFields = new ArrayList<>();
        if(localVariables == null){
            return localFields;
        }
        for(LocalVariableNode localV: localVariables){
            if(!localV.name.equals("this")){
                localFields.add(new FieldObject(localV.name, AccessModifier.DEFAULT, localV.signature, cleanUp(localV.desc), null));
                classListAdd(localV.desc);
            }
        }
    
        return localFields;
    }
    //returns an array of implemented class names
    private ArrayList<String> getImplementedClasses(List<String> interfaces){
        ArrayList<String> implemented = new ArrayList<>();
        if(interfaces == null){
            return implemented;
        }
        for(String i : interfaces) {
            implemented.add(i);
            classListAdd(i);
        }
        return implemented;
    }
    //Returns an array of  intsructionObjects asociated with a amethod 
    private ArrayList<InstructionObject> getInstructions(InsnList instructions) {
        // TODO: need to add any references from individual instructions to classes to the classList using classListAdd();
        ArrayList<InstructionObject> returnList = new ArrayList<>();
        if(instructions == null){
            return returnList;
        }
        for(AbstractInsnNode abn: instructions){
            if(abn.getType() == AbstractInsnNode.FIELD_INSN){
                FieldInsnNode node = ((FieldInsnNode) abn);
                FieldInstructionsObject fObj = new FieldInstructionsObject(node.name,node.desc, InstructionType.FIELD);
                returnList.add(fObj);

            }
            else if(abn.getType() == AbstractInsnNode.METHOD_INSN){
                MethodInsnNode node = ((MethodInsnNode) abn);
                MethodInstructionsObject mObj = new MethodInstructionsObject(node.name, node.owner, node.itf, InstructionType.METHOD);
                classListAdd(mObj.owner);
                returnList.add(mObj);
            }
            else if(abn.getType() == AbstractInsnNode.VAR_INSN){
                VarInsnNode node = ((VarInsnNode) abn);
                VariableInstructionsObject vObj = new VariableInstructionsObject(node.var, InstructionType.VARIABLE);
                returnList.add(vObj);
            }
        }
        return returnList;
    }

    // Returns the Variable Operation associated with OPCode
    // private VarOP getLoadOrStore(int opCode) {
    //     VarOP op = VarOP.DEFAULT;
    //     if(((opCode & Opcodes.ILOAD) !=0 ) || ((opCode & Opcodes.ALOAD) !=0 ) || 
    //     ((opCode & Opcodes.DLOAD) !=0 ) || ((opCode & Opcodes.LLOAD) !=0 ) || ((opCode & Opcodes.FLOAD) !=0 )){
    //             op = VarOP.LOAD;
    //         }
    //     if(((opCode & Opcodes.ISTORE) !=0 ) || ((opCode & Opcodes.ASTORE) !=0 ) || ((opCode & Opcodes.DSTORE) !=0 ) ||
    //     ((opCode & Opcodes.LSTORE) !=0 ) || ((opCode & Opcodes.FSTORE) !=0 )){
    //             op = VarOP.STORE;
    //         }   
    //     return op;
    // }
    // gets an array of FieldObjects from a Methods Parameters
    private ArrayList<FieldObject> getArguments(Type[] types, List<ParameterNode> parameters){
        ArrayList<FieldObject> fieldObjects = new ArrayList<>();
        if(parameters == null || types ==null){
            return fieldObjects;
        }
        for(int i=0; i<parameters.size();i++){
            fieldObjects.add(new FieldObject(parameters.get(i).name, AccessModifier.DEFAULT, null, cleanUp(types[i].getClassName()), null));
            classListAdd(types[i].getClassName());
        }
        return fieldObjects;
    }
    //Local Variables all start with L 
    //so its important to remove them so our converter 
    //does not think its a seperate class
    private String removeL(String className) {
        if(className.startsWith("L")){
            className = className.substring(1);
        }
        return className;
    }
    //Returns an ArrayList of FieldObjects
    private ArrayList<FieldObject> getFields(List<FieldNode> fields){
        ArrayList<FieldObject> fieldObjects = new ArrayList<>();
        if(fields == null){
            return fieldObjects;
        }
        for(FieldNode f : fields) {
                String fname = cleanUp(f.name);
                AccessModifier faccess = getAccess(f.access);
                String fsig = f.signature;
                String ftype = f.desc;
                // classListAdd(removeL(ftype));
                Object fvalue = f.value;
                //System.out.println(f.value);
                fieldObjects.add(new FieldObject(fname, faccess, fsig, cleanUp(ftype), fvalue));
            }
            return fieldObjects;
    }
    // Returns an accessModifer based on Opcodes
    public AccessModifier getAccess(Integer accessint) {
        AccessModifier access = AccessModifier.DEFAULT;
        if((accessint & Opcodes.ACC_PUBLIC) != 0) {
            access = AccessModifier.PUBLIC;
        } else if((accessint & Opcodes.ACC_PRIVATE) != 0) {
            access = AccessModifier.PRIVATE;
        } else if((accessint & Opcodes.ACC_PROTECTED) != 0) {
            access = AccessModifier.PROTECTED;
        } else if((accessint & Opcodes.ACC_STATIC) != 0) {
            access = AccessModifier.STATIC;
        }
        return access;
    }
    //cleans up class names into a more digestable form for our Object Types
    private String cleanUp(String name){
        if(name.contains("/")){
            name = name.substring(name.lastIndexOf("/") + 1);
        }
        if(name.contains(";")){
            name = name.replace(";", "");
        }
        return name;
    }
   
}