package sisepe.api.imports;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RestImportacao {
    private final ServiceImport servicoImportacao;

    @GetMapping("/importar")
    public ResponseEntity<String> importarSecoes() {
        try {
            servicoImportacao.importarSecoesCsv();
            servicoImportacao.importarSedesPolosCsv();
            servicoImportacao.importarSedesZonasCsv();
            return ResponseEntity.ok("Importação concluída com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao importar: %s".formatted(e.getMessage()));
        }
    }
}
