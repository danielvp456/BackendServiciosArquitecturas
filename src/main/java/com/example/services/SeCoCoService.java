/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.services;

import com.example.PersistenceManager;

import com.example.models.CitaDeVacunacion;
import com.example.models.CitaDeVacunacionDTO;
import com.example.models.ReporteDeSintomas;
import com.example.models.ReporteDeSintomasDTO;
import com.example.models.UbicacionVacunador;
import com.example.models.UbicacionVacunadorDTO;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;

/**
 *
 * @author Mauricio
 */
@Path("/competitors")
@Produces(MediaType.APPLICATION_JSON)
public class SeCoCoService {

    @PersistenceContext(unitName = "mongoPU")
    EntityManager entityManager;

    @PostConstruct
    public void init() {
        try {
            entityManager = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
/*
    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompetitorsByName(@PathParam("name") String name) {
        TypedQuery<Competitor> query = (TypedQuery<Competitor>) entityManager.createQuery("SELECT c FROM Competitor c WHERE c.name = :name");
        List<Competitor> competitors = query.setParameter("name", name).getResultList();
        return Response.status(200).header("Access-Control-Allow-Origin",
                "*").entity(competitors).build();
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        Query q = entityManager.createQuery("select u from Competitor u order by u.surname ASC");
        List<Competitor> competitors = q.getResultList();
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(competitors).build();
    }

    @GET
    @Path("/inicialesPorA")
    @Produces(MediaType.APPLICATION_JSON)
    public Response nombreInicialA() {
       Query q = entityManager.createQuery("select p.name from Competitor p WHERE p.name LIKE 'A%'");
       List<Competitor> competitors = q.getResultList();
       return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(competitors).build();
    }
    
    @GET
    @Path("/buscarProductos")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getAllProducts() {
        Query q = entityManager.createQuery("SELECT p FROM Competitor p");
        List<Competitor> competitors = q.getResultList();
        JSONObject rta = new JSONObject();
        for (Competitor e : competitors) {
            if (e.getProducto() != null) {
                rta.put(e, e.getProducto());
            }
        }
        return rta;
    }

    @POST
    @Path("/vehicle")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCompetitor(CompetitorDTO competitor) {
        Competitor c = new Competitor();
        JSONObject rta = new JSONObject();
        c.setAddress(competitor.getAddress());
        c.setAge(competitor.getAge());
        c.setCellphone(competitor.getCellphone());
        c.setCity(competitor.getCity());
        c.setCountry(competitor.getCountry());
        c.setName(competitor.getName());
        c.setSurname(competitor.getSurname());
        c.setTelephone(competitor.getTelephone());
        c.setVehicle(competitor.getVehicle());
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(c);
            entityManager.getTransaction().commit();
            entityManager.refresh(c);
            rta.put("competitor_id", c.getId());
        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            c = null;
        } finally {
            entityManager.clear();
            entityManager.close();
        }
        return Response.status(200).header("Access-Control-Allow-Origin",
                "*").entity(rta.toJSONString()).build();
    }

    @POST
    @Path("/producto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCompetitorWithProduct(CompetitorDTO competitor) {
        Competitor c = new Competitor();
        JSONObject rta = new JSONObject();
        c.setAddress(competitor.getAddress());
        c.setAge(competitor.getAge());
        c.setCellphone(competitor.getCellphone());
        c.setCity(competitor.getCity());
        c.setCountry(competitor.getCountry());
        c.setName(competitor.getName());
        c.setSurname(competitor.getSurname());
        c.setTelephone(competitor.getTelephone());
        c.setVehicle(competitor.getVehicle());
        c.setProducto(competitor.getProducto());
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(c);
            entityManager.getTransaction().commit();
            entityManager.refresh(c);
            rta.put("competitor_id", c.getId());
        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            c = null;
        } finally {
            entityManager.clear();
            entityManager.close();
        }
        return Response.status(200).header("Access-Control-Allow-Origin",
                "*").entity(rta.toJSONString()).build();
    }
    */
    
    @POST
    @Path("/agendarCita")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCompetitorAgendarCita(CitaDeVacunacionDTO citaDTO) {
        CitaDeVacunacion c = new CitaDeVacunacion();
        JSONObject rta = new JSONObject();
        c.setId(citaDTO.getId());
        c.setLugar(citaDTO.getLugar());
        c.setPaciente(citaDTO.getPaciente());
        c.setVacunador(citaDTO.getVacunador());
        c.setFecha(citaDTO.getFecha());
        c.setHora(citaDTO.getHora());
        
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(c);
            entityManager.getTransaction().commit();
            entityManager.refresh(c);
            rta.put("cita_vacunacion_id", c.getId());
        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            c = null;
        } finally {
            entityManager.clear();
            entityManager.close();
        }
        return Response.status(200).header("Access-Control-Allow-Origin",
                "*").entity(rta.toJSONString()).build();
    }
    
    
    @GET
    @Path("/consultarCita/{paciente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getConsiltarCitas(@PathParam("paciente") String paciente) {
        System.out.println(paciente);
        TypedQuery<CitaDeVacunacion> query = (TypedQuery<CitaDeVacunacion>) entityManager.createQuery("SELECT c FROM CitaDeVacunacion c WHERE c.paciente = :paciente");
        List<CitaDeVacunacion> citaVacunacion = query.setParameter("paciente", paciente).getResultList();
        return Response.status(200).header("Access-Control-Allow-Origin",
                "*").entity(citaVacunacion).build();
    }
    
    @DELETE
    @Path("/borraCita/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarCita(@PathParam("id") String id) {
        System.out.println(id);
        TypedQuery<CitaDeVacunacion> query = (TypedQuery<CitaDeVacunacion>) entityManager.createQuery("DELETE  FROM CitaDeVacunacion");
        System.out.println(id+"aaaaaaaaaaaa");
        
        //List<CitaDeVacunacion> citaVacunacion = query.setParameter("id", id).getResultList();
        System.out.println(id+"bbbbbbbbbbbb");
        return Response.status(200).header("Access-Control-Allow-Origin",
                "*").entity("").build();
    }
    @GET
    @Path("/rutasVacunacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRutasVacunacion() {
        
        TypedQuery<CitaDeVacunacion> query = (TypedQuery<CitaDeVacunacion>) entityManager.createQuery("SELECT c FROM CitaDeVacunacion c");
        List<CitaDeVacunacion> citasVacunacion = query.getResultList();
        return Response.status(200).header("Access-Control-Allow-Origin",
                "*").entity(citasVacunacion).build();
    }

    @GET
    @Path("/asignacionDiaria/{vacunador}/{fecha}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAsignacionDiaria(@PathParam("vacunador") String vacunador,
            @PathParam("fecha") String fecha) {
        System.out.println(vacunador + "---------- "+ fecha);
        Query q = entityManager.createQuery("SELECT e FROM CitaDeVacunacion e WHERE e.vacunador = :vacunador AND e.fecha = :fecha");

            q.setParameter("vacunador", vacunador);
            q.setParameter("fecha", fecha);

            List<CitaDeVacunacion> usuario = q.getResultList();

            if (usuario.isEmpty()==false) {
                return Response.status(200).header("Access-Control-Allow-Origin", "").entity(usuario).build();
            }
            //throw new NotAuthorizedException("las credeenciales no coinciden",Response.status(200).header("Access-Control-Allow-Origin", "").entity(usuario).build());
            return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(usuario).build();
    }
    
    @POST
    @Path("/reportarSintomasVacunacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportarSintomas(ReporteDeSintomasDTO citaDTO) {
        ReporteDeSintomas c = new ReporteDeSintomas();
        JSONObject rta = new JSONObject();
        c.setId(citaDTO.getId());
        c.setListaDeSíntomas(citaDTO.getListaDeSíntomas());
        c.setPaciente(citaDTO.getPaciente());
        c.setFecha(citaDTO.getFecha());
        c.setHora(citaDTO.getHora());
        
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(c);
            entityManager.getTransaction().commit();
            entityManager.refresh(c);
            rta.put("cita_vacunacion_id", c.getId());
        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            c = null;
        } finally {
            entityManager.clear();
            entityManager.close();
        }
        return Response.status(200).header("Access-Control-Allow-Origin",
                "*").entity(rta.toJSONString()).build();
    }
    
    
    @POST
    @Path("/ubicacionVacunador")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ubicacionVacunador(UbicacionVacunadorDTO ubicacionDTO) {
        UbicacionVacunador c = new UbicacionVacunador();
        JSONObject rta = new JSONObject();
        
        c.setId(ubicacionDTO.getId());
        c.setLugarActual(ubicacionDTO.getLugarActual());
        c.setVacunador(ubicacionDTO.getVacunador());
        c.setFecha(ubicacionDTO.getFecha());
        c.setHora(ubicacionDTO.getHora());
        
                
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(c);
            entityManager.getTransaction().commit();
            entityManager.refresh(c);
            rta.put("cita_vacunacion_id", c.getId());
        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            c = null;
        } finally {
            entityManager.clear();
            entityManager.close();
        }
        return Response.status(200).header("Access-Control-Allow-Origin",
                "*").entity(rta.toJSONString()).build();
    }
    
    @OPTIONS
    public Response cors(@javax.ws.rs.core.Context HttpHeaders requestHeaders) {
        return Response.status(200).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS").header("Access-Control-Allow-Headers", "AUTHORIZATION, content-type, accept").build();
    }

}
