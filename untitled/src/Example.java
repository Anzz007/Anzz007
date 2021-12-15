public class Example {

    public static void main(String args[]) {
        int[] a = {1,2,3,5,7,7,50,10,15,5};
        int big = a[0];
        int secondBig = a[1];
        for(int i= 1; i<a.length; i++) {
            if (a[i] > big) {
                secondBig = big;
                big =  a[i];
            } else if (secondBig < a[i]){
                secondBig = a[i];
            }
        }
        System.out.println(secondBig);
    }
}
