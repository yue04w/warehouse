package Assignment1.Cloth_Warehouse.controller;

import Assignment1.Cloth_Warehouse.models.Brand;
import Assignment1.Cloth_Warehouse.models.Item;
import Assignment1.Cloth_Warehouse.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/addItem")
    public String showAddForm(Model model) {
        model.addAttribute("item", new Item());
        return "addItem";
    }

    @PostMapping("/addItem")
    public String addItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        itemRepository.save(item);
        redirectAttributes.addFlashAttribute("message", "Item added successfully!");
        return "redirect:/listItems";
    }

    @GetMapping("/listItems")
    public String listItems(
            @RequestParam(required = false) Boolean sortByBrand,
            @RequestParam(required = false) Brand filterBrand,
            @RequestParam(required = false) Integer filterYear,
            Model model) {

        if (filterBrand != null && filterYear != null) {
            // Filter by brand and year
            List<Item> filteredItems = itemRepository.findByBrandAndYearOfCreation(filterBrand, filterYear);
            model.addAttribute("items", filteredItems);
            model.addAttribute("message", "Showing items for brand " + filterBrand + " from year " + filterYear);
        } else if (sortByBrand != null && sortByBrand) {
     
            List<Item> sortedItems = itemRepository.findAllByOrderByBrandAsc();
            model.addAttribute("items", sortedItems);
            model.addAttribute("message", "Items sorted by brand");
        } else {
            // Show all items
            List<Item> allItems = itemRepository.findAll();
            model.addAttribute("items", allItems);
        }
        
        return "listItems";
    }
}