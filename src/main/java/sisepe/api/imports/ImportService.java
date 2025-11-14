package sisepe.api.imports;

import com.opencsv.CSVReaderHeaderAware;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sisepe.api.municipio.Municipio;
import sisepe.api.municipio.RepoMunicipio;
import sisepe.api.polo.Polo;
import sisepe.api.polo.RepoPolo;
import sisepe.api.secao.RepoSecao;
import sisepe.api.secao.Secao;
import sisepe.api.zona.RepoZona;
import sisepe.api.zona.Zona;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImportService {
    private final RepoSecao secaoRepo;
    private final RepoMunicipio municipioRepo;
    private final RepoPolo poloRepo;
    private final RepoZona zonaRepo;

    public void importarSecoesCsv() {
        log.info("Importando seções");
        InputStream resource = getClass().getResourceAsStream("/db/migration/csv/secoes.csv");
        if (resource == null) return;

        List<Secao> secoes = new ArrayList<>();
        try (CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new InputStreamReader(resource))) {
            Map<String, String> linha;
            while ((linha = reader.readMap()) != null) {
                int codTse = Integer.parseInt(linha.get("COD_MUNICIPIO_TSE"));
                String nomeMunicipio = linha.get("NOM_MUNICIPIO");
                int numSecao = Integer.parseInt(linha.get("NUM_SECAO"));
                int numZona = Integer.parseInt(linha.get("NUM_ZONA"));
                int numPolo = Integer.parseInt(linha.get("NUM_POLO"));

                final Polo polo = poloRepo.findById(numPolo).orElseGet(() -> poloRepo.save(new Polo(numPolo)));

                final Zona zona = zonaRepo.findById(numZona).orElseGet(() -> zonaRepo.save(new Zona(numZona)));

                final Municipio municipio = municipioRepo.findById(codTse).orElseGet(() -> {
                    Municipio municipioNovo = new Municipio(codTse, nomeMunicipio);
                    municipioNovo.setPolo(polo);
                    municipioNovo.inserirZona(zona);
                    municipioNovo = municipioRepo.save(municipioNovo);
                    return municipioNovo;
                });

                polo.inserirMunicipio(municipio);
                zona.inserirMunicipio(municipio);
                poloRepo.save(polo);
                zonaRepo.save(zona);

                Optional<Secao> secaoOpt = secaoRepo.findById(numSecao);
                if (secaoOpt.isEmpty()) {
                    Secao secao = new Secao(numSecao);
                    secao.setMunicipio(municipio);
                    secao.setPolo(polo);
                    secao.setZona(zona);
                    secoes.add(secao);
                }
            }
        } catch (Exception e) {
            log.error("Erro ao importar seções", e);
        }

        secaoRepo.saveAll(secoes);
    }

    public void importarSedesPolosCsv() {
        log.info("Importando sedes de polos");
        InputStream resource = getClass().getResourceAsStream("/db/migration/csv/polos-sedes.csv");
        if (resource == null) return;

        try (CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new InputStreamReader(resource))) {
            Map<String, String> linha;
            while ((linha = reader.readMap()) != null) {
                int numPolo = Integer.parseInt(linha.get("POLO"));
                int codTse = Integer.parseInt(linha.get("SEDE"));

                Municipio municipio = municipioRepo.findById(codTse).orElseThrow();
                Polo polo = poloRepo.findById(numPolo).orElseThrow();

                polo.setMunicipioSede(municipio);
                poloRepo.save(polo);
            }
        } catch (Exception e) {
            log.error("Erro ao importar sedes de polos", e);
        }
    }

    public void importarSedesZonasCsv() {
        log.info("Importando sedes de zonas");
        InputStream resource = getClass().getResourceAsStream("/db/migration/csv/zonas-sedes.csv");
        if (resource == null) return;

        try (CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new InputStreamReader(resource))) {
            Map<String, String> linha;
            while ((linha = reader.readMap()) != null) {
                int numZona = Integer.parseInt(linha.get("NUM_ZONA"));
                int codTse = Integer.parseInt(linha.get("COD_MUNICIPIO_SEDE"));

                Zona zona = zonaRepo.findById(numZona).orElseThrow();
                Municipio municipio = municipioRepo.findById(codTse).orElseThrow();

                zona.setMunicipioSede(municipio);
                zonaRepo.save(zona);
            }
        } catch (Exception e) {
            log.error("Erro ao importar sedes de zonas");
        }
    }
}
