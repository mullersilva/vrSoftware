package com.vr.Software.repositories;

import com.vr.Software.entities.ProductEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query(value = "SELECT * FROM products", nativeQuery = true)
    List<ProductEntity> findAllProducts();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM products WHERE codigo = :codigo", nativeQuery = true)
    void deleteByCodigoNative(@Param("codigo") Long codigo);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO products (codigo, descricao, preco) VALUES (:codigo, :descricao, :preco)", nativeQuery = true)
    void persistProductNative(@Param("codigo") Long codigo, @Param("descricao") String descricao, @Param("preco") Double preco);

}
