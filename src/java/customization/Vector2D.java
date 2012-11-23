
package customization;

/**
 * Vector 2d que tiene la caracteristica que se ordena por la coordenada y
 * @author alberto
 */
public class Vector2D implements Comparable<Vector2D> {
    private Object x;
    private Comparable y;

    public Vector2D(Object x , Comparable y){
        this.x=x;
        this.y=y;
    }
    
    /**
     * @return the x
     */
    public Object getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(Object x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public Comparable getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(Comparable y) {
        this.y = y;
    }

    @Override
    public int compareTo(Vector2D o) {
        return o.y.compareTo(this.y);
    }
    
    public String toString(){
        return "("+this.x+","+this.y+")";
    }
    
}
