package testcrud2

//import com.google.gson.

import grails.transaction.Transactional
import grails.converters.JSON

import javax.persistence.criteria.CriteriaBuilder;
import org.hibernate.criterion.CriteriaSpecification

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Transactional(readOnly=true)
class BiodataController {

    //    def scaffold = Biodata
    @Transactional
    def create(){
        def instance = new Biodata(request.JSON)
        if (!instance.hasErrors()) {
            response.status = 200 // OK
            render instance as JSON
            System.out.println("inside if")
//            Client client = Client.create()
//            WebResource webResource = client.resource("http://localhost:8383/Testing/create")
//            ClientResponse response = webResource.accept("application/json").get(ClientResponse.class)
//            if (response.getStatus() != 200) {
////                            throw new RuntimeException("Failed : HTTP error code : "+response.getStatus());
//            }
        }
        else {
            System.out.println("inside else")
            response.status = 400 // Bad Request
            render instance.errors.allErrors as JSON
        }
        
    }
    
    def index() {
        def result = Biodata.createCriteria().list (params){
            resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
            projections{
                property('id','id')
                property('nama','nama')
                property('tempat','tempat')
                property('tanggal','tanggal')
                property('alamat','alamat')
            }
        }
        response.status= 200
        render  result as JSON
    }
}
