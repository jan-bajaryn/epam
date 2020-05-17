package by.epam.cafe.service.parser.full;

import by.epam.cafe.entity.impl.Product;
import by.epam.cafe.entity.impl.ProductGroup;
import by.epam.cafe.entity.struct.OptionalNullable;
import by.epam.cafe.service.parser.helper.ValidateAndPutter;
import by.epam.cafe.service.parser.parts.PriceParser;
import by.epam.cafe.service.parser.parts.WeightParser;
import by.epam.cafe.service.parser.parts.IdParser;
import by.epam.cafe.service.parser.parts.ProductGroupInProductParser;

import java.util.Map;
import java.util.Optional;

public class ProductParser {

    private final ValidateAndPutter validateAndPutter = ValidateAndPutter.getInstance();

    private final IdParser idParser = new IdParser();
    private final ProductGroupInProductParser productGroupInProductParser = new ProductGroupInProductParser();
    private final PriceParser priceParser = new PriceParser();
    private final WeightParser weightParser = new WeightParser();

    public Product parseProduct(Map<String, String> redirect, String productGroup, String price, String weight) {
        OptionalNullable<Integer> productGroupOpt = productGroupInProductParser.parse(productGroup);
        OptionalNullable<Integer> priceOpt = priceParser.parse(price);
        OptionalNullable<Integer> weightOpt = weightParser.parse(weight);

        boolean result = validateAndPutter.validateAndPut(redirect, productGroupOpt, "product_group", productGroup) &
                validateAndPutter.validateAndPut(redirect, priceOpt, "price", price) &
                validateAndPutter.validateAndPut(redirect, weightOpt, "weight", weight);


        if (result) {
            return Product.newBuilder()
                    .productGroup(
                            ProductGroup.newBuilder().id(productGroupOpt.get()).build()
                    )
                    .weight(weightOpt.get())
                    .price(priceOpt.get())
                    .build();

        } else {
            return null;
        }
    }

    public Product parseProductWithId(Map<String, String> redirect, String id, String productGroup, String price, String weight) {
        Product product = parseProduct(redirect, productGroup, price, weight);
        if (product != null) {

            OptionalNullable<Integer> idOpt = idParser.parse(id);
            boolean result = validateAndPutter.validateAndPut(redirect, idOpt, "id", id);
            if (result) {
                product.setId(idOpt.get());
                return product;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}