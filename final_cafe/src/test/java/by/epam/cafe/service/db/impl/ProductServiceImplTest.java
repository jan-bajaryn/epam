package by.epam.cafe.service.db.impl;

import by.epam.cafe.entity.db.impl.Product;
import by.epam.cafe.entity.db.impl.ProductGroup;
import by.epam.cafe.service.DatabaseManager;
import by.epam.cafe.service.exception.ServiceException;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static by.epam.cafe.config.Configuration.MAX_PAGINATION_ELEMENTS;
import static org.testng.Assert.*;

public class ProductServiceImplTest {
    private ProductServiceImpl productServiceImpl = new ProductServiceImpl();

    private final DatabaseManager databaseManager = new DatabaseManager();

    @Test
    public void testFindAll() throws ServiceException {
        assertEquals(productServiceImpl.findAll().size(), productServiceImpl.count());
    }

    @Test

    public void testNotNullFields() throws ServiceException {
        List<Product> all = productServiceImpl.findAll();
        for (Product product : all) {
            assertNotNull(product.getPrice());
            assertNotNull(product.getWeight());
            assertNotNull(product.getId());
        }
    }

    @Test
    public void testFindAllByPart1() throws ServiceException {
        List<Product> allByPart = productServiceImpl.findAllByPart(1);
        assertEquals(allByPart.size(), MAX_PAGINATION_ELEMENTS);
    }

    @Test
    public void testFindEntityById() throws ServiceException {
        Product entityById = productServiceImpl.findEntityById(1);
        assertEquals(entityById.getId(), Integer.valueOf(1));
        assertEquals(entityById.getPrice(), Integer.valueOf(1004));
        assertEquals(entityById.getWeight(), Integer.valueOf(239));
        assertEquals(entityById.getProductGroup().getId(), Integer.valueOf(1));
    }

    @Test
    public void testFindAllByProductGroupNull() throws ServiceException {
        List<Product> allByProductGroupNull = productServiceImpl.findAllByProductGroupNull();
        List<Product> all = productServiceImpl.findAll().stream()
                .filter(p -> p.getProductGroup() == null)
                .collect(Collectors.toList());
        assertEquals(allByProductGroupNull, all);
    }

    @Test
    public void testFindAllByProductGroupNotDisabled() throws ServiceException {
        List<Product> allByProductGroupNull = productServiceImpl.findAllByProductGroupNotDisabled();
        List<Product> all = productServiceImpl.findAll().stream()
                .filter(p -> p.getProductGroup() != null && !p.getProductGroup().isDisabled())
                .collect(Collectors.toList());
        assertEquals(allByProductGroupNull, all);
    }


    @Test
    public void testDeleteByIdWithConnections() throws ServiceException {
        try {
            assertFalse(productServiceImpl.deleteById(1));
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testDeleteById() throws ServiceException {
        try {
            assertTrue(productServiceImpl.deleteById(24));
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testDeleteByIdNotExisting() throws ServiceException {
        try {
            assertFalse(productServiceImpl.deleteById(1000));
        } finally {
            databaseManager.reset();
        }
    }


    @Test
    public void testDeleteWithConnections() throws ServiceException {
        try {
            assertFalse(productServiceImpl.delete(Product.newBuilder().id(1).build()));
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testDelete() throws ServiceException {
        try {
            assertTrue(productServiceImpl.delete(Product.newBuilder().id(24).build()));
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testDeleteNotExisting() throws ServiceException {
        try {
            assertFalse(productServiceImpl.delete(Product.newBuilder().id(1000).build()));
        } finally {
            databaseManager.reset();
        }
    }


    @Test
    public void testCreateNull() throws ServiceException {
        try {
            Product product = productServiceImpl.create(null);
            assertNull(product);
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testCreate() throws ServiceException {
        try {
            Product product = Product.newBuilder()
                    .price(111)
                    .weight(4)
                    .build();
            Product newProduct = productServiceImpl.create(product);
            assertNotNull(newProduct);
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testCreateWithGroup() throws ServiceException {
        try {
            Product product = Product.newBuilder()
                    .price(111)
                    .productGroup(ProductGroup.newBuilder().id(1).build())
                    .weight(4)
                    .build();
            Product newProduct = productServiceImpl.create(product);
            assertNotNull(newProduct);
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testCreateWithGroupNotExisting() throws ServiceException {
        try {
            Product product = Product.newBuilder()
                    .price(111)
                    .productGroup(ProductGroup.newBuilder().id(1000).build())
                    .weight(4)
                    .build();
            Product newProduct = productServiceImpl.create(product);
            assertNull(newProduct);
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testUpdateNull() throws ServiceException {
        try {
            boolean result = productServiceImpl.update(null);
            assertFalse(result);
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testUpdate() throws ServiceException {
        try {
            Product build = Product.newBuilder()
                    .id(1)
                    .weight(4)
                    .price(6)
                    .build();
            boolean result = productServiceImpl.update(build);
            assertTrue(result);
        } finally {
            databaseManager.reset();
        }
    }
}