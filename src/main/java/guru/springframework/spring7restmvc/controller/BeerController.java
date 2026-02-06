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
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerService;

    @PatchMapping("/{beerId}")
    public ResponseEntity<Void> patchBeerById(@PathVariable("beerId") UUID beerId,
                                              @RequestBody Beer beer) {
        log.debug("Patch Beer - in controller");
        beerService.patchBeerById(beerId, beer);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{beerId}")
    public ResponseEntity<Void> deleteBeerById(@PathVariable("beerId") UUID beerId) {
        log.debug("Delete Beer by Id - in controller");
        beerService.deleteBeerById(beerId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<Void> updateBeerById(@PathVariable("beerId") UUID beerId,
                                               @RequestBody Beer beer) {
        log.debug("Update Beer - in controller");
        beerService.updateBeerById(beerId, beer);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Void> createBeer(@RequestBody Beer beer) {
        log.debug("Create Beer - in controller");

        Beer savedBeer = beerService.createBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "/api/v1/beer/" + savedBeer.getId());

        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Beer> listBeers() {
        log.debug("List Beers - in controller");
        return beerService.listBeers();
    }

    @RequestMapping(value = "/{beerId}", method = RequestMethod.GET)
    public Beer getBeerById(@PathVariable("beerId") UUID beerId) {
        log.debug("Get Beer by Id - in controller");
        return beerService.getBeerById(beerId);
    }

}
