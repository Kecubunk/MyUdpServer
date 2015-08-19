package MyUdpServer;

import java.io.*;

public class SerializedObject {

    private static ByteArrayInputStream byteArrayInputStream;
    private static ByteArrayOutputStream byteArrayOutputStream;
    private static ObjectInput objInput;
    private static ObjectOutput objOutput;

    public static byte[] Serialize(Object obj) {
        byteArrayOutputStream = new ByteArrayOutputStream();
        objOutput = null;

        try {
            objOutput = new ObjectOutputStream(byteArrayOutputStream);
            objOutput.writeObject(obj);

            return byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objOutput != null)
                try {
                    objOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            if (byteArrayOutputStream != null)
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return null;
    }

    public static Object Deserialize(byte[] bytes) {
        byteArrayInputStream = new ByteArrayInputStream(bytes);
        objInput = null;

        try {
            objInput = new ObjectInputStream(byteArrayInputStream);
            Object obj = objInput.readObject();

            return obj;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objInput != null)
                try {
                    objInput.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            if (byteArrayInputStream != null);
                try {
                    byteArrayInputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return null;
    }
}
