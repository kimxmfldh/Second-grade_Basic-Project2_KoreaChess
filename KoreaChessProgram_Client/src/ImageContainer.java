import javax.swing.ImageIcon;

public class ImageContainer {
	ImageIcon mainimage = new ImageIcon("./image/mainimage.png");
	ImageIcon maintitle = new ImageIcon("./image/maintitle.png");
	ImageIcon koreachessplate = new ImageIcon("./image/koreachessplate.png");
	ImageIcon giveup = new ImageIcon("./image/giveup.png");
	
	ImageIcon hanJoll = new ImageIcon("./image/hanjoll.png");
	ImageIcon hanPho = new ImageIcon("./image/hanpho.png");
	ImageIcon hanCha = new ImageIcon("./image/hancha.png");
	ImageIcon hanSang = new ImageIcon("./image/hansang.png");
	ImageIcon hanMa = new ImageIcon("./image/hanma.png");
	ImageIcon hanSa = new ImageIcon("./image/hansa.png");
	ImageIcon hanKing = new ImageIcon("./image/hanking.png");
	
	ImageIcon choJoll = new ImageIcon("./image/chojoll.png");
	ImageIcon choPho = new ImageIcon("./image/chopho.png");
	ImageIcon choCha = new ImageIcon("./image/chocha.png");
	ImageIcon choSang = new ImageIcon("./image/chosang.png");
	ImageIcon choMa = new ImageIcon("./image/choma.png");
	ImageIcon choSa = new ImageIcon("./image/chosa.png");
	ImageIcon choKing = new ImageIcon("./image/choking.png");

	ImageIcon defaultImage = new ImageIcon("");

	ImageIcon findImage(String input) {
		if(input.equals("hanJoll")) {
			return hanJoll;
		} else if (input.equals("hanPho")) {
			return hanPho;
		}else if (input.equals("hanCha")) {
			return hanCha;
		}else if (input.equals("hanSang")) {
			return hanSang;
		}else if (input.equals("hanMa")) {
			return hanMa;
		}else if (input.equals("hanSa")) {
			return hanSa;
		}else if (input.equals("hanKing")) {
			return hanKing;
		}else if (input.equals("choJoll")) {
			return choJoll;
		}else if (input.equals("choPho")) {
			return choPho;
		}else if (input.equals("choCha")) {
			return choCha;
		}else if (input.equals("choSang")) {
			return choSang;
		}else if (input.equals("choMa")) {
			return choMa;
		}else if (input.equals("choSa")) {
			return choSa;
		}else if (input.equals("choKing")) {
			return choKing;
		}
		return defaultImage;
	}
}
