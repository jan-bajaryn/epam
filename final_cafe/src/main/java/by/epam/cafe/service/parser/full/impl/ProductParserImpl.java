package by.epam.cafe.service.parser.full.impl;

import by.epam.cafe.entity.db.impl.Product;
import by.epam.cafe.entity.db.impl.ProductGroup;
import by.epam.cafe.entity.struct.OptionalNullable;
import by.epam.cafe.service.parser.full.ProductParser;
import by.epam.cafe.service.parser.helper.impl.ValidateAndPutter;
import by.epam.cafe.service.parser.parts.impl.PriceParser;
import by.epam.cafe.service.parser.parts.impl.WeightParser;
import by.epam.cafe.service.parser.parts.impl.IdParser;
import by.epam.cafe.service.parser.parts.impl.ProductGroupInProductParser;

import java.util.Map;

public class ProductParserImpl implements ProductParser {

    private final ValidateAndPutter validateAndPutter = ValidateAndPutter.getInstance();

    private final IdParser idParser = new IdParser();
    private final ProductGroupInProductParser productGroupInProductParser = new ProductGroupInProductParser();
    private final PriceParser priceParser = new PriceParser();
    private final WeightParser weightParser = new WeightParser();

    /**
     * @param redirect     Map to return what parameter is valid, and value with
     *                     what parameter was in input
     *                     First String in the map is the name of parameter
     *                     Second String in the map is value of input in parameter
     *                     or information about existing error in the map
     *                     For example {street, abcde} means that input for
     *                     parameter of name "street" was "abcde"
     *                     {street_error, true} means that in parameter
     *                     of name "street" was error.
     * @param productGroup Product group parameter, id of product group {@link Product#getProductGroup()}
     * @param price        Price parameter {@link Product#getPrice()}
     * @param weight       Weight parameter {@link Product#getWeight()}
     * @return {@link Product} with parsed parameters if all params are valid
     * or {@code null} if any of the parameter is invalid
     */
    @Override
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


    /**
     * @param redirect     Map to return what parameter is valid, and value with
     *                     what parameter was in input
     *                     First String in the map is the name of parameter
     *                     Second String in the map is value of input in parameter
     *                     or information about existing error in the map
     *                     For example {street, abcde} means that input for
     *                     parameter of name "street" was "abcde"
     *                     {street_error, true} means that in parameter
     *                     of name "street" was error.     *
     * @param id           identifier of Product {@link Product#getId()}
     * @param productGroup identifier of ProductGroup in Product {@link Product#getProductGroup()}
     * @param price        Price parameter {@link Product#getPrice()}
     * @param weight       Weight parameter {@link Product#getWeight()}
     * @return {@link Product} with parsed parameters if all params are valid
     * or {@code null} if any of the parameter is invalid
     */
    @Override
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