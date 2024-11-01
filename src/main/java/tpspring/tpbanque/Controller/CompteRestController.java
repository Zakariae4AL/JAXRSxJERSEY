package tpspring.tpbanque.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tpspring.tpbanque.REPOSITORIES.CompteRepository;
import tpspring.tpbanque.entities.Compte;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
@Component
@Path("/banque")
public class CompteRestController {

    @Autowired
    private CompteRepository compteRepository;

    @Path("/comptes")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Compte> getComptes() {
        return compteRepository.findAll();
    }

    @Path("/comptes/{id}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getCompte(@PathParam("id") Long id) {
        Compte compte = compteRepository.findById(id).orElse(null);
        if (compte == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(compte).build();
    }

    @Path("/comptes")
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Compte addCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    @Path("/comptes/{id}")
    @DELETE
    public void deleteCompte(@PathParam("id") Long id) {
        compteRepository.deleteById(id);
    }

    @Path("/comptes/{id}")
    @PUT
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateCompte(@PathParam("id") Long id, Compte compte) {
        if (!compteRepository.existsById(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        compte.setId(id);
        Compte updatedCompte = compteRepository.save(compte);
        return Response.ok(updatedCompte).build();
    }
}