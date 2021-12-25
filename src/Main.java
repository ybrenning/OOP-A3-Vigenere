public class Main {
    public static void main(String[] args) {
        Cipher.printTable();
        Cipher cipher = new Cipher("scheune");
        System.out.println(cipher.encrypt("Gedicht.txt", "Gedicht-Encrypted.txt"));
    }
}
