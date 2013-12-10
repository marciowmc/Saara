package adapters;

import java.util.List;

import beans.Favoritos;
import br.com.saara.R;
  
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class FavoritoAdapter extends BaseAdapter{
	
	private static int TRANSPARENT_50 = 125;
	private static int TRANSPARENT_80 = 215;
    private List<Favoritos> listLojas;
    private int imgCategoria;
    Context context;
    private int[] rgbDark;
    private int[] rgbLight;
 
    //Classe utilizada para instanciar os objetos do XML
    private LayoutInflater inflater;
    private int idLayout;
     
    public FavoritoAdapter(Context context, List<Favoritos> listLojas, int idLayout ) {
        this.listLojas = listLojas;
        this.context = context;
        this.idLayout = idLayout;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
 
    public void addItem(final Favoritos item) {
        this.listLojas.add(item);
        //Atualizar a lista caso seja adicionado algum item
        notifyDataSetChanged();
    }    
        
	@Override
	public int getCount() {
        return listLojas.size();
    }
   
	@Override
	public Object getItem(int position) {
        return listLojas.get(position);
    }
 
	@Override
	public long getItemId(int position) {
        return position;
    }
	
	public void setData(List<Favoritos> newList) {
		this.listLojas = newList;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
         
        Favoritos estadoVO = listLojas.get(position);
 
        ViewHolder holder;
   
        if (convertView == null) {
            convertView = inflater.inflate(idLayout, null);
    
            holder = new ViewHolder();
            holder.icone = (ImageView)convertView.findViewById(R.id.imgIcon);
            holder.pin   = (ImageView)convertView.findViewById(R.id.imgPin);
            holder.title = (TextView) convertView.findViewById(R.id.txtTitle);
           
            convertView.setTag(holder);
            
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
 
        holder.title.setText(estadoVO.getNome_loja());
        holder.icone.setImageResource(estadoVO.getDrawable_categoria());
        holder.pin.setImageResource(R.drawable.favoritos_pressed);
        
        int rgb[] = estadoVO.getRgbColor();
        
		holder.title.setBackgroundColor(Color.argb(TRANSPARENT_80,rgb[0], rgb[1],rgb[2] ));
		holder.icone.setBackgroundColor(Color.rgb(rgb[0], rgb[1],rgb[2]));
		holder.pin.setBackgroundColor(Color.argb(TRANSPARENT_50, rgb[0], rgb[1],rgb[2] ));
        
        return convertView;
 
    }
  
    static class ViewHolder {
        public ImageView icone;
        public TextView title;
        public ImageView pin;
    }
}