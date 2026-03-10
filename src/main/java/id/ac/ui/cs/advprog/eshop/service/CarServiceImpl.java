package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CarServiceImpl implements CarCommandService, CarQueryService {

    private final CarRepository carRepository;
    private final CarIdGenerator idGenerator;

    public CarServiceImpl(CarRepository carRepository, CarIdGenerator idGenerator) {
        this.carRepository = carRepository;
        this.idGenerator = idGenerator;
    }

    @Override
    public Car create(Car car) {
        if (car.getCarId() == null) {
            car.setCarId(idGenerator.generate());
        }
        return carRepository.create(car);
    }

    @Override
    public List<Car> findAll() {
        Iterator<Car> carIterator = carRepository.findAll();
        List<Car> allCar = new ArrayList<>();
        carIterator.forEachRemaining(allCar::add);
        return allCar;
    }

    @Override
    public Car findById(String carId) {
        return carRepository.findById(carId);
    }

    @Override
    public void update(String carId, Car car) {
        Car existingCar = carRepository.findById(carId);
        if (existingCar != null) {
            existingCar.setCarName(car.getCarName());
            existingCar.setCarColor(car.getCarColor());
            existingCar.setCarQuantity(car.getCarQuantity());
        }
    }

    @Override
    public void delete(String carId) {
        carRepository.delete(carId);
    }
}