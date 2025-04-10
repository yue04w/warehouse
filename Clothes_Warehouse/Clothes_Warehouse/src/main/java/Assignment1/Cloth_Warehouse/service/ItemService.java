package Assignment1.Cloth_Warehouse.service;

import Assignment1.Cloth_Warehouse.models.Item;
import Assignment1.Cloth_Warehouse.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public List<Item> getAllItemsSorted(Sort sort) {
        return itemRepository.findAll(sort);
    }

    public Item addItem(Item item) throws IllegalArgumentException {
        if (item.getPrice() <= 1000) {
            throw new IllegalArgumentException("Price must be more than 1000");
        }
        if (item.getYearOfCreation() <= 2021) {
            throw new IllegalArgumentException("Year of creation must be more than 2021");
        }
        return itemRepository.save(item);
    }
}
