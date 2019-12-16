package temp;

/**
 * @author liuhaiyan
 * @date 2019-11-22 17:37
 */
public class Demo {
        private int num;

        public int inc(){
            for(int i=0; i<10; i++){
                num = i;
            }
            return num;
        }

        public static void main(String[] args){
            new Demo().inc();
        }
}