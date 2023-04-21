package ru.gb.perov.Part3HW8.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.gb.perov.Part3HW8.Data.Product;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Query("select p from Product p where p.productPrice < :minParam")
    List<Product> findAllMin(@Param("minParam") Double minParam);

    @Query("select p from Product p where p.productPrice > :maxParam")
    List<Product> findAllMax(@Param("maxParam") Double maxParam);

    @Query("select p from Product p where p.productPrice between :minParam AND :maxParam")
    List<Product> findAllBetween(@Param("minParam") Double minParam, @Param("maxParam") Double maxParam);

    @Query("select min(p.productPrice) from Product p")
    Double findMinPrice();

    @Query("select max(p.productPrice) from Product p")
    Double findMaxPrice();
}
