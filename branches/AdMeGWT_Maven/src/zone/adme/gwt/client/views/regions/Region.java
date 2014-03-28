package zone.adme.gwt.client.views.regions;

public enum Region
{
    North
    {
        @Override
        public <IN, OUT> OUT accept(RegionVisitor<IN, OUT> visitor, IN in)
        {
            return visitor.visitNorth(in);
        }
    },
    East
    {
        @Override
        public <IN, OUT> OUT accept(RegionVisitor<IN, OUT> visitor, IN in)
        {
            return visitor.visitEast(in);
        }
    },
    Center
    {
        @Override
        public <IN, OUT> OUT accept(RegionVisitor<IN, OUT> visitor, IN in)
        {
            return visitor.visitCenter(in);
        }
    },
    West
    {
        @Override
        public <IN, OUT> OUT accept(RegionVisitor<IN, OUT> visitor, IN in)
        {
            return visitor.visitWest(in);
        }
    },
    South
    {
        @Override
        public <IN, OUT> OUT accept(RegionVisitor<IN, OUT> visitor, IN in)
        {
            return visitor.visitSouth(in);
        }
    };

    public abstract <IN, OUT> OUT accept(RegionVisitor<IN, OUT> visitor, IN in);
}
