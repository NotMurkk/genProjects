public class test {
    public static void main(String[] args) {
        String reversedStr = reverseString("Hello");
        System.out.println(reversedStr);
    }
    public static String reverseString(String str){
        // asdfa
        // a f d s a
        String reveredString = "";
        for(int i = str.length()-1; i >= 0; i--){
            // a f d s a
            reveredString = reveredString + str.charAt(i);
        }
        return reveredString;
    }
}
