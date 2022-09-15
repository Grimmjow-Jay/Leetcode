package practice.demo;

/**
 * @author Jay Yang
 * @date 2022/9/13
 */
public class SubStringDemo {

    public static void main(String[] args) {

        String productName = "思念白糯小圆子（小汤圆）1KG*10袋/件";
        String specification = "规格:1kg*10袋/件";
        String productSimpleName = productSimpleName(productName, specification);
        System.out.println(productSimpleName);
    }

    private static String productSimpleName(String productName, String specification) {
        int specificationColonIndex = specification.indexOf(":");
        if (specificationColonIndex >= 0) {
            specification = specification.substring(specificationColonIndex + 1);
        }

        String productNameUpperCase = productName.toUpperCase();
        String specificationUpperCase = specification.toUpperCase();
        boolean endsWithSpecification = productNameUpperCase.endsWith(specificationUpperCase);
        if (endsWithSpecification) {
            int endIndex = productNameUpperCase.lastIndexOf(specificationUpperCase);
            return productName.substring(0, endIndex);
        }
        return productName;
    }

}
