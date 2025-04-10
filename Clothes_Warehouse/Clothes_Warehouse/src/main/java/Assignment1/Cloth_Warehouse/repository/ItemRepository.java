package Assignment1.Cloth_Warehouse.repository;

import Assignment1.Cloth_Warehouse.models.Brand;
import Assignment1.Cloth_Warehouse.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByBrandAndYearOfCreation(Brand brand, int yearOfCreation);
    
     List<Item> findAllByOrderByBrandAsc();
}