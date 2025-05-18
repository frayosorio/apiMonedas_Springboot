package monedas.api.infraestructura.integracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import monedas.api.dominio.DTOs.CapitalDto;

@Service
public class PaisCliente {

    @Autowired
    private RestTemplate restTemplate;

    public CapitalDto obtenerCapital(String nombre) {
        String url = "http://localhost:8080/paises/capital/" + nombre;

        ResponseEntity<CapitalDto> response = restTemplate.exchange(url, HttpMethod.GET, null, CapitalDto.class);

        return response.getBody();
    }

}
