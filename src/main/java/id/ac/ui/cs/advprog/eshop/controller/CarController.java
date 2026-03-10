package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarCommandService;
import id.ac.ui.cs.advprog.eshop.service.CarQueryService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {

    private static final String REDIRECT_CAR_LIST = "redirect:/car/list";

    private final CarCommandService carService;

    private final CarQueryService carQueryService;

    public CarController(CarCommandService carService, CarQueryService carQueryService) {
        System.out.println(">>> CarController LOADED");
        this.carService = carService;
        this.carQueryService = carQueryService;
    }

    @GetMapping("/create")
    public String createCarPage(Model model) {
        model.addAttribute("car", new Car());
        return "CreateCar"; 
    }

    @PostMapping("/create")
    public String createCarPost(@ModelAttribute Car car) {
        carService.create(car);
        return REDIRECT_CAR_LIST;
    }

    @GetMapping("/list")
    public String carListPage(Model model) {
        List<Car> allCars = carQueryService.findAll();
        model.addAttribute("cars", allCars);
        return "CarList"; 
    }

    @GetMapping("/edit/{carId}")
    public String editCarPage(@PathVariable String carId, Model model) {
        Car car = carQueryService.findById(carId);
        if (car == null) return REDIRECT_CAR_LIST;
        model.addAttribute("car", car);
        return "EditCar"; 
    }

    @PostMapping("/edit")
    public String editCarPost(@ModelAttribute Car car) {
        carService.update(car.getCarId(), car);
        return REDIRECT_CAR_LIST;
    }

    @PostMapping("/delete")
    public String deleteCar(@RequestParam("carId") String carId) {
        carService.delete(carId);
        return REDIRECT_CAR_LIST;
    }
}
