package adapters;

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
	
	private static int TRANSPARENT_50 = 125;
	private static int TRANSPARENT_80 = 215;
	
    private List<Categorias> listCategorias;
    Context context;
 
    //Classe utilizada para instanciar os objetos do XML
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
        //Atualizar a lista caso seja adicionado algum item
        notifyDataSetChanged();
    }    
        
	public int getCount() {
        return listCategorias.size();
    }
   
	public Object getItem(int position) {
        return listCategorias.get(position);
    }
 
	public long getItemId(int position) {
        return position;
    }

	public View getView(int position, View convertView, ViewGroup viewGroup) {
         
        Categorias estadoVO = listCategorias.get(position);
 
        //O ViewHolder irâ€¡ guardar a instâ€°ncias dos objetos do estado_row
        ViewHolder holder;
   
        //Quando o objeto convertView nâ€¹o for nulo nâ€”s nâ€¹o precisaremos inflar
        //os objetos do XML, ele serâ€¡ nulo quando for a primeira vez que for carregado
        if (convertView == null) {
            convertView = inflater.inflate(idLayout, null);
    
            //Cria o Viewholder e guarda a instâ€°ncia dos objetos
            holder = new ViewHolder();
            holder.qtdLojas = (TextView) convertView.findViewById(R.id.txtQtdLojas);
            holder.icone = (ImageView)convertView.findViewById(R.id.imgIcon);
            holder.title = (TextView) convertView.findViewById(R.id.txtTitle);
           
            convertView.setTag(holder);
        } else {
            //pega o ViewHolder para ter um acesso râ€¡pido aos objetos do XML
            //ele sempre passarâ€¡ por aqui quando,por exemplo, for efetuado uma rolagem na tela 
            holder = (ViewHolder) convertView.getTag();
        }
 
        holder.title.setText(estadoVO.getText());
        holder.qtdLojas.setText(""+estadoVO.getQtdLojas());
        holder.icone.setImageResource(estadoVO.getIcon());
        
        int rgb[] = estadoVO.getRgbColor();
        
		holder.title.setBackgroundColor(Color.argb(215,rgb[0], rgb[1],rgb[2] ));
		holder.icone.setBackgroundColor(Color.rgb(rgb[0], rgb[1],rgb[2]));
		holder.qtdLojas.setBackgroundColor(Color.argb(125,rgb[0], rgb[1],rgb[2]));
		
       
        return convertView;
 
    }
  
    //Criada esta classe estâ€¡tica para guardar a referÂ�ncia dos objetos abaixo
    static class ViewHolder {
        public ImageView icone;
        public TextView title;
        public TextView qtdLojas;
    }
}