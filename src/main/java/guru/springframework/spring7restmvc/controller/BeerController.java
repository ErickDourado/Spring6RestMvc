package guru.springframework.spring7restmvc.controller;

import guru.springframework.spring7restmvc.model.Beer;
import guru.springframework.spring7restmvc.services.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerController {

    public static final String BEER_PATH = "/api/v1/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";

    private final BeerService beerService;

    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity<Void> patchBeerById(@PathVariable("beerId") UUID beerId,
                                              @RequestBody Beer beer) {
        log.debug("Patch Beer - in controller");
        beerService.patchBeerById(beerId, beer);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity<Void> deleteBeerById(@PathVariable("beerId") UUID beerId) {
        log.debug("Delete Beer by Id - in controller");
        beerService.deleteBeerById(beerId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(BEER_PATH_ID)
    public ResponseEntity<Void> updateBeerById(@PathVariable("beerId") UUID beerId,
                                               @RequestBody Beer beer) {
        log.debug("Update Beer - in controller");
        beerService.updateBeerById(beerId, beer);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(BEER_PATH)
    public ResponseEntity<Void> createBeer(@RequestBody Beer beer) {
        log.debug("Create Beer - in controller");

        Beer savedBeer = beerService.createBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, BEER_PATH + "/" + savedBeer.getId());

        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
    }

    @GetMapping(BEER_PATH)
    public List<Beer> listBeers() {
        log.debug("List Beers - in controller");
        return beerService.listBeers();
    }

    @GetMapping(value = BEER_PATH_ID)
    public Beer getBeerById(@PathVariable("beerId") UUID beerId) {
        log.debug("Get Beer by Id - in controller");
        return beerService.getBeerById(beerId);
    }

}
