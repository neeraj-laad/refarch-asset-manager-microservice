package application.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import model.Asset;
import utils.CassandraConnection;

@Path("/")
public class AssetResource {
	
	CassandraConnection cc = new CassandraConnection();
	AssetService assetRepo = new AssetService(cc);

    @GET
    @Path("/assets")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Asset> getAssets(){ 
    	List<Asset> assets = new ArrayList<>();
    	assets = assetRepo.getAssets();
    	return assets;
	}

	@GET
	@Path("/assets/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Asset> getAssetById(@PathParam("id") String id) {
		List<Asset> assets = new ArrayList<>();
		assets = assetRepo.getAssetById(id);
		return assets;
	}

	@GET
	@Path("assets/type/{type}")
	@Produces(MediaType.APPLICATION_JSON) 
	public List<Asset> getAssetsByType(@PathParam("type") String type) {
		List<Asset> assets = new ArrayList<>();
		assets = assetRepo.getAssetsByType(type);
		return assets;
	}

	@POST
	@Path("/assets")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
	public String createAsset(Asset asset) {
		assetRepo.createAsset(asset);
		return "Asset with ID "+asset.getId()+" got created";
	}


}
