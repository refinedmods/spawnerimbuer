package com.raoulvdberge.spawnerimbuer;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.*;

public class SpawnerImbuerTransformer implements IClassTransformer {
    private boolean hasInitialized;

    class SpawnerImbuerClassVisitor extends ClassVisitor {
        public SpawnerImbuerClassVisitor(int api, ClassVisitor next) {
            super(api, next);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            if (name.equals("isActivated") || name.equals("h")) {
                SpawnerImbuer.LOGGER.info("Found isActivated (h)");

                return new SpawnerImbuerMethodVisitor(api, cv.visitMethod(access, name, desc, signature, exceptions));
            } else {
                return cv.visitMethod(access, name, desc, signature, exceptions);
            }
        }
    }

    class SpawnerImbuerMethodVisitor extends MethodVisitor {
        public SpawnerImbuerMethodVisitor(int api, MethodVisitor next) {
            super(api, next);
        }

        @Override
        public void visitInsn(int opcode) {
            if (opcode == Opcodes.IRETURN) {
                SpawnerImbuer.LOGGER.info("Patching IRETURN");

                visitIntInsn(Opcodes.BIPUSH, 1);
            }

            super.visitInsn(opcode);
        }
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] data) {
        if (!hasInitialized) {
            hasInitialized = true;

            SpawnerImbuer.LOGGER.info("ACTIVATED!");
        }

        if (transformedName.equals("net.minecraft.tileentity.MobSpawnerBaseLogic")) {
            SpawnerImbuer.LOGGER.info("Found net.minecraft.tileentity.MobSpawnerBaseLogic");

            final ClassReader reader = new ClassReader(data);
            final ClassWriter writer = new ClassWriter(8);

            reader.accept(new SpawnerImbuerClassVisitor(Opcodes.ASM5, writer), 8);

            return writer.toByteArray();
        }

        return data;
    }
}
