package zone.adme.gwt.client.places;

public interface PlaceVisitor<IN, OUT>
{
	OUT visit(InitPlace initPlace, IN in);
    
    OUT visit(SignPlace signPlace, IN in);
    
    OUT visit(RegisterPlace registerPlace, IN in);
    
    OUT visit(PControlPlace pControlPlace, IN in);
    
    OUT visit(ShowPlace showPlace, IN in);
    
}
