package example.cashcard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/cashcards")
class CashCardController {
    private final CashCardRepository cashCardRepository;

    private CashCardController(CashCardRepository cashCardRepository) {
        this.cashCardRepository = cashCardRepository;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<CashCard> findById( @PathVariable Long requestedId ) {
        if(requestedId != 99L)
            return ResponseEntity.notFound().build();

        Optional<CashCard> cashCardOptional = cashCardRepository.findById(requestedId);
        if(cashCardOptional.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(cashCardOptional.get());
    }
}