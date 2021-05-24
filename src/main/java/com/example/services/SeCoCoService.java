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
import com.example.models.ReporteVacunacion;
import com.example.models.ReporteVacunacionDTO;
import com.example.models.UbicacionVacunador;
import com.example.models.UbicacionVacunadorDTO;
import java.util.ArrayList;

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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


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
        System.out.println(id + "aaaaaaaaaaaa");

        //List<CitaDeVacunacion> citaVacunacion = query.setParameter("id", id).getResultList();
        System.out.println(id + "bbbbbbbbbbbb");
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
        System.out.println(vacunador + "---------- " + fecha);
        Query q = entityManager.createQuery("SELECT e FROM CitaDeVacunacion e WHERE e.vacunador = :vacunador AND e.fecha = :fecha");

        q.setParameter("vacunador", vacunador);
        q.setParameter("fecha", fecha);

        List<CitaDeVacunacion> usuario = q.getResultList();

        if (usuario.isEmpty() == false) {
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

    @GET
    @Path("/pacientesVacunadosPeriodoDeTiempo/{fecha}/{fechaFinal}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPacientesVacunadosPeriodoDeTiempo(@PathParam("fecha") String fecha, @PathParam("fechaFinal") String fechaFinal) {
        List<ReporteVacunacion> usuario = new ArrayList<ReporteVacunacion>();

        String aux = fecha;
        String auxDos = fechaFinal;
        int con = 0;

        aux = aux.substring(0, 2);
        auxDos = auxDos.substring(0, 2);

        int pacientesVacunados = 0;

        int tam = Integer.parseInt(auxDos) - Integer.parseInt(aux) + 1;

        //  System.out.println("tam  " + tam);
        for (int i = 0; i < tam; i++) {
            Query q = entityManager.createQuery("SELECT e FROM ReporteVacunacion e WHERE e.fecha = :fecha");

            q.setParameter("fecha", fecha);

            usuario = q.getResultList();

            pacientesVacunados += usuario.size();

            con = Integer.parseInt(aux) + 1;

            aux = Integer.toString(con);
            //   System.out.println("con   " +  con);

            fecha = Integer.toString(con) + fecha.substring(2, fecha.length());

            //  System.out.println("fecha   " +  fecha);
        }

        if (usuario.isEmpty() == false) {
            return Response.status(200).header("Access-Control-Allow-Origin", "").entity(pacientesVacunados).build();
        }
        //throw new NotAuthorizedException("las credeenciales no coinciden",Response.status(200).header("Access-Control-Allow-Origin", "").entity(usuario).build());
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(pacientesVacunados).build();
    }

    @POST
    @Path("/reporteVacunacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reporteVacunacion(ReporteVacunacionDTO reporteVacunacionDTO) {
        ReporteVacunacion c = new ReporteVacunacion();
        JSONObject rta = new JSONObject();

        c.setId(reporteVacunacionDTO.getId());
        c.setFecha(reporteVacunacionDTO.getFecha());
        c.setHora(reporteVacunacionDTO.getHora());
        c.setLugar(reporteVacunacionDTO.getLugar());
        c.setPaciente(reporteVacunacionDTO.getPaciente());
        c.setVacuna(reporteVacunacionDTO.getVacuna());
        c.setDosis(reporteVacunacionDTO.getDosis());

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
    @Path("/pacientesSinVacunar/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPacientesSinVacunar() {

        List<CitaDeVacunacion> datos = new ArrayList<CitaDeVacunacion>();
        List<String> numeros = new ArrayList<String>();

        Query q = entityManager.createQuery("SELECT e FROM ReporteVacunacion e");
        Query qDos = entityManager.createQuery("SELECT e FROM CitaDeVacunacion e");

        List<ReporteVacunacion> usuario = q.getResultList();
        List<CitaDeVacunacion> usuarioDos = qDos.getResultList();

        if (usuario.isEmpty() == false) {
            boolean ver = false;
            for (int i = 0; i < usuarioDos.size(); i++) {
                ver = false;
                for (int j = 0; j < usuario.size(); j++) {

                    if (usuario.get(j).getPaciente().equalsIgnoreCase(usuarioDos.get(i).getPaciente())) {

                        numeros.add("-1");
                        ver = true;
                    }
                }
                if (ver == false) {
                    numeros.add(Integer.toString(i));

                }
            }

            for (int i = 0; i < usuarioDos.size(); i++) {

                if (!numeros.get(i).equals("-1")) {
                    datos.add(usuarioDos.get(Integer.parseInt(numeros.get(i))));
                }
            }
        }

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(datos).build();
    }

    @GET
    @Path("/citasVacunacionPorFecha/{fecha}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCitasVacunacionPorFecha(@PathParam("fecha") String fecha) {

        Query q = entityManager.createQuery("SELECT e FROM CitaDeVacunacion e WHERE e.fecha = :fecha");
        q.setParameter("fecha", fecha);

        List<CitaDeVacunacion> usuario = q.getResultList();

        if (usuario.isEmpty() == false) {
            return Response.status(200).header("Access-Control-Allow-Origin", "").entity(usuario).build();
        }
        //throw new NotAuthorizedException("las credeenciales no coinciden",Response.status(200).header("Access-Control-Allow-Origin", "").entity(usuario).build());
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(usuario).build();
    }

    @GET
    @Path("/pacientesVacunadosPorFecha/{fecha}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPacientesVacunadosPorFecha(@PathParam("fecha") String fecha) {

        Query q = entityManager.createQuery("SELECT e FROM ReporteVacunacion e WHERE e.fecha = :fecha");
        q.setParameter("fecha", fecha);

        List<ReporteVacunacion> usuario = q.getResultList();

        if (usuario.isEmpty() == false) {
            return Response.status(200).header("Access-Control-Allow-Origin", "").entity(usuario).build();
        }
        //throw new NotAuthorizedException("las credeenciales no coinciden",Response.status(200).header("Access-Control-Allow-Origin", "").entity(usuario).build());
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(usuario).build();
    }

    @GET
    @Path("/tasaDeVacunacionPorFecha/{fecha}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTasaDeVacunacionPorFecha(@PathParam("fecha") String fecha) {

        Query q = entityManager.createQuery("SELECT e FROM ReporteVacunacion e WHERE e.fecha = :fecha");
        q.setParameter("fecha", fecha);

        List<ReporteVacunacion> usuario = q.getResultList();

        if (usuario.isEmpty() == false) {
            return Response.status(200).header("Access-Control-Allow-Origin", "").entity(usuario.size()).build();
        }
        //throw new NotAuthorizedException("las credeenciales no coinciden",Response.status(200).header("Access-Control-Allow-Origin", "").entity(usuario).build());
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(usuario.size()).build();
    }

    @GET
    @Path("/pacientesConSintomasPostVacunacion/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPacientesConSintomasPostVacunacion() {

        Query q = entityManager.createQuery("SELECT e FROM ReporteDeSintomas e");

        List<ReporteVacunacion> usuario = q.getResultList();

        if (usuario.isEmpty() == false) {
            return Response.status(200).header("Access-Control-Allow-Origin", "").entity(usuario).build();
        }
        //throw new NotAuthorizedException("las credeenciales no coinciden",Response.status(200).header("Access-Control-Allow-Origin", "").entity(usuario).build());
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(usuario).build();
    }

    @OPTIONS
    public Response cors(@javax.ws.rs.core.Context HttpHeaders requestHeaders) {
        return Response.status(200).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS").header("Access-Control-Allow-Headers", "AUTHORIZATION, content-type, accept").build();
    }
}
