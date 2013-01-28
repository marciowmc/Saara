package adapters;

import java.util.List;

import beans.Lojas;
import br.com.saara.R;
import br.com.saara.util.Utilidade;
  
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class LojaAdapter extends BaseAdapter{
	
	private static int TRANSPARENT_50 = 125;
	private static int TRANSPARENT_80 = 215;
    private List<Lojas> listLojas;
    private int imgCategoria;
    Context context;
    private int[] rgbDark;
    private int[] rgbLight;
 
    //Classe utilizada para instanciar os objetos do XML
    private LayoutInflater inflater;
    private int idLayout;
     
    public LojaAdapter(Context context, List<Lojas> listLojas, int idLayout , int img, int[] rgb , int[] rgbDark) {
        this.listLojas = listLojas;
        this.context = context;
        this.idLayout = idLayout;
        this.imgCategoria = img;
        this.rgbLight = rgb;
        this.rgbDark = rgbDark;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
 
    public void addItem(final Lojas item) {
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
	
	public void setData(List<Lojas> newList) {
		this.listLojas = newList;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
         
        Lojas estadoVO = listLojas.get(position);
 
        ViewHolder holder;
   
        if (convertView == null) {
            convertView = inflater.inflate(idLayout, null);
    
            holder = new ViewHolder();
            holder.icone = (ImageView)convertView.findViewById(R.id.imgIcon);
            holder.favorito   = (ImageView)convertView.findViewById(R.id.imgPin);
            holder.title = (TextView) convertView.findViewById(R.id.txtTitle);
           
            convertView.setTag(holder);
            
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
 
        if(Utilidade.checkLojaFavorita(context, estadoVO.getIdLoja())){
        	holder.favorito.setImageResource(R.drawable.favoritos_pressed);
        }
        
        holder.title.setText(estadoVO.getNome());
        holder.icone.setImageResource(imgCategoria);
        
        
        if (position % 2 == 0){
        	holder.title.setBackgroundColor(Color.argb(215,rgbLight[0], rgbLight[1],rgbLight[2] ));
    		holder.icone.setBackgroundColor(Color.rgb(rgbLight[0], rgbLight[1],rgbLight[2]));
    		holder.favorito.setBackgroundColor(Color.argb(125,rgbLight[0], rgbLight[1],rgbLight[2]));
        }else{
        	holder.title.setBackgroundColor(Color.argb(215,rgbDark[0], rgbDark[1],rgbDark[2] ));
    		holder.icone.setBackgroundColor(Color.rgb(rgbDark[0], rgbDark[1],rgbDark[2]));
    		holder.favorito.setBackgroundColor(Color.argb(125,rgbDark[0], rgbDark[1],rgbDark[2]));
        }
       
        return convertView;
 
    }
  
    static class ViewHolder {
        public ImageView icone;
        public TextView title;
        public ImageView favorito;
    }
}