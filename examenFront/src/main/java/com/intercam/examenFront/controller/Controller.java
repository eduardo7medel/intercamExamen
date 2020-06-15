package com.intercam.examenFront.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ArrayIterator;
import com.google.gson.JsonObject;
import com.intercam.examenFront.dto.Client;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {

    private static String BASEURL = "http://localhost:8090/examen";

    @GetMapping("index")
    @DateTimeFormat(pattern="dd-MMM-YYYY")
    public ModelAndView index(Model model){
        RestTemplate plantilla = new RestTemplate();
        Iterable<Client> clients = plantilla.getForObject("http://localhost:8090/examen/getAllClients", Iterable.class);
        model.addAttribute("clients", clients);
        return new ModelAndView("index");
    }

    @GetMapping("editClient")
    public ModelAndView editClient(int id,Model model){

        RestTemplate plantilla = new RestTemplate();
        Client client = plantilla.getForObject(BASEURL+"/getClientById?id="+id, Client.class);

        model.addAttribute("client", client);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("fechaNacimiento", sdf.format(client.getFechaNacimiento()));
        return new ModelAndView("editClient");
    }

    @GetMapping("findById")
    public ModelAndView findById(int id,Model model){
        List<Client> clients = new ArrayList<Client>();
        RestTemplate plantilla = new RestTemplate();
        Client client = plantilla.getForObject(BASEURL+"/getClientById?id="+id, Client.class);
        clients.add(client);

        model.addAttribute("clients", clients);
        return new ModelAndView("index");
    }

    @GetMapping("addClient")
    public ModelAndView addClient(Model model){
        model.addAttribute("client", new Client());
        return new ModelAndView("editClient");
    }

    @GetMapping("saveClient")
    public ModelAndView saveClient(String nombre, String app, String apm, String fn, BigDecimal ingresos, String cp, Model model){

        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("nombre", nombre);
        jsonRequest.addProperty("apellidoPaterno", app);
        jsonRequest.addProperty("apellidoMaterno", apm);
        jsonRequest.addProperty("fechaNacimiento", fn);
        jsonRequest.addProperty("ingresos", ingresos);
        jsonRequest.addProperty("codigoPostal", cp);
        RequestConfig config = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(60000).build();
        HttpClientBuilder builder = HttpClientBuilder.create();
        builder.setDefaultRequestConfig(config);
        CloseableHttpClient httpClient = builder.build();

        HttpPost request = new HttpPost(BASEURL+"/addClient");
        request.setEntity(new StringEntity(jsonRequest.toString(),"UTF-8"));
        request.addHeader("content-type", "application/json;charset=UTF-8");
        CloseableHttpResponse response = null;
        System.out.println("Enviando petición");
        try {
            response = httpClient.execute(request);
            String resp = EntityUtils.toString(response.getEntity());
            System.out.println(resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return index(model);
    }

    @GetMapping("deleteClient")
    public ModelAndView deleteClient(Integer id, Model model){

        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("id", id);
        RequestConfig config = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(60000).build();
        HttpClientBuilder builder = HttpClientBuilder.create();
        builder.setDefaultRequestConfig(config);
        CloseableHttpClient httpClient = builder.build();

        HttpPost request = new HttpPost(BASEURL+"/deleteClient");

        request.setEntity(new StringEntity(jsonRequest.toString(),"UTF-8"));
        request.addHeader("content-type", "application/json;charset=UTF-8");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
            String resp = EntityUtils.toString(response.getEntity());
            System.out.println(resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return index(model);
    }

    @GetMapping("editClientById")
    public ModelAndView editClient(Integer id, String nombre, String app, String apm, String fn, BigDecimal ingresos, String cp, Model model){

        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("id", id);
        jsonRequest.addProperty("nombre", nombre);
        jsonRequest.addProperty("apellidoPaterno", app);
        jsonRequest.addProperty("apellidoMaterno", apm);
        jsonRequest.addProperty("fechaNacimiento", fn);
        jsonRequest.addProperty("ingresos", ingresos);
        jsonRequest.addProperty("codigoPostal", cp);
        RequestConfig config = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(60000).build();
        HttpClientBuilder builder = HttpClientBuilder.create();
        builder.setDefaultRequestConfig(config);
        CloseableHttpClient httpClient = builder.build();

        HttpPut request = new HttpPut(BASEURL+"/updateClient");
        request.setEntity(new StringEntity(jsonRequest.toString(),"UTF-8"));
        request.addHeader("content-type", "application/json;charset=UTF-8");
        CloseableHttpResponse response = null;
        System.out.println("Enviando petición");
        try {
            response = httpClient.execute(request);
            String resp = EntityUtils.toString(response.getEntity());
            System.out.println(resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return index(model);
    }

}
