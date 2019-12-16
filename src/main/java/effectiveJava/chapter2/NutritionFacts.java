package effectiveJava.chapter2;

/**
 * 遇到多个构造器参数时考虑用构建器
 * @author liuhaiyan
 * @date 2019-12-04 16:41
 */
public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sedium;
    private final int carbohydrate;

    public NutritionFacts(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }
    public NutritionFacts(int servingSize, int servings, int calories) {
        this(servingSize, servings, calories, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat) {
        this(servingSize, servings, calories, fat, 0);
    }
    public NutritionFacts(int servingSize, int servings, int calories, int fat, int sedium) {
        this(servingSize, servings, calories, fat, sedium, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat, int sedium, int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sedium = sedium;
        this.carbohydrate = carbohydrate;
    }


    // 采用Builder模式
    public static class Builder {
        private final int servingSize;
        private final int servings;

        private  int calories = 0;
        private  int fat = 0;
        private  int sedium = 0;
        private  int carbohydrate = 0;

        public Builder(int servingSize, int servings) {
            this.servings = servings;
            this.servingSize = servingSize;
        }

        public Builder calories (int val) {
            carbohydrate = val;
            return this;
        }
        public Builder fat(int val) {
            fat = val;
            return this;
        }
        public Builder sedium (int val) {
            sedium = val;
            return this;
        }
        public Builder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }


    }

    public NutritionFacts(Builder builder) {
        this.calories = builder.calories;
        this.sedium = builder.sedium;
        this.carbohydrate = builder.carbohydrate;
        this.servings = builder.servings;
        this.fat = builder.fat;
        this.servingSize = builder.servingSize;
    }


    // 这样客户端如果想使用NutritionFacts这个类，就可以使用如下的代码
    public static void main(String[] args) {
        NutritionFacts nutritionFacts = new Builder(2,3).calories(2).fat(2).carbohydrate(4).sedium(3).build();
    }

}
