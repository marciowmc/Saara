package adapters;

import java.util.ArrayList;
import java.util.List;

import beans.Categorias;
import br.com.saara.R;
  
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class CategoriaAdapter extends BaseAdapter{
	
    private List<Categorias> listCategorias;
    Context context;
 
    private LayoutInflater inflater;
    private int idLayout;
     
    public CategoriaAdapter(Context context, List<Categorias> listCategorias, int idLayout ) {
        this.listCategorias = listCategorias;
        this.context = context;
        this.idLayout = idLayout;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
 
    public void addItem(final Categorias item) {
        this.listCategorias.add(item);
        notifyDataSetChanged();
    }    
        
	@Override
	public int getCount() {
        return listCategorias.size();
    }
   
	@Override
	public Object getItem(int position) {
        return listCategorias.get(position);
    }
 
	@Override
	public long getItemId(int position) {
        return position;
    }
	
	public void setData(ArrayList<Categorias> categorias) {
		this.listCategorias = categorias;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
         
        Categorias estadoVO = listCategorias.get(position);
 
        ViewHolder holder;
   
        if (convertView == null) {
            convertView = inflater.inflate(idLayout, null);
    
            holder = new ViewHolder();
            holder.qtdLojas = (TextView) convertView.findViewById(R.id.txtQtdLojas);
            holder.icone = (ImageView)convertView.findViewById(R.id.imgIcon);
            holder.title = (TextView) convertView.findViewById(R.id.txtTitle);
           
            convertView.setTag(holder);
            
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
 
        holder.title.setText(estadoVO.getText());
        holder.qtdLojas.setText(String.valueOf(estadoVO.getQtdLojas()));
        holder.icone.setImageResource(estadoVO.getIcon());
        
        int rgb[] = estadoVO.getRgbColor();
        
		holder.title.setBackgroundColor(Color.argb(215,rgb[0], rgb[1],rgb[2] ));
		holder.icone.setBackgroundColor(Color.rgb(rgb[0], rgb[1],rgb[2]));
		holder.qtdLojas.setBackgroundColor(Color.argb(125,rgb[0], rgb[1],rgb[2]));
		
        return convertView;
 
    }
  
    static class ViewHolder {
        public ImageView icone;
        public TextView title;
        public TextView qtdLojas;
    }
}