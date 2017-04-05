package ChapterFour;

/**
 * 矩阵乘法的Strassen算法
 * @author 俊
 *
 */
public class Strassen {
	// 设置输入输出变量
	private Integer[][] a;
	private Integer[][] b;
	private Integer[][] c;// 用于保存通过普通的矩阵相乘得到结果
	private Integer[][] cd;// 用于保存通过普通分治发得到的结果
	private Integer[][] cds;// 用于保存通过strassen算法得到的结果

	// 构造函数，传入外界的a和b
	public Strassen(Integer[][] a, Integer[][] b) {
		super();
		this.a = a;
		this.b = b;
		this.c = new Integer[a.length][b[0].length];
		this.cd = new Integer[a.length][b[0].length];
		this.cds = new Integer[a.length][b[0].length];
	}
	/**
	 * 两个矩阵相加的函数
	 * @param a
	 * @param b
	 * @return
	 */
	private Integer[][] MatrixAdd(Integer[][]a,Integer[][]b){
		Integer a1 = a.length;
		Integer a2 = a[0].length;
		Integer b1 = b.length;
		Integer b2 = b[0].length;
		Integer[][] c = new Integer[a1][a2];
		if(a1!=b1||a2!=b2){
			System.out.println("两个矩阵的行数或者列数不相等，不能进行相加");
		}
		for(Integer i=0;i<a1;i++){
			for(Integer j=0;j<a2;j++){
				c[i][j] = a[i][j]+b[i][j];
			}
		}
		return c;
	}

	/**
	 * 两个矩阵相减的函数
	 * @param a
	 * @param b
	 * @return
	 */
	private Integer[][] MatrixMinus(Integer[][]a,Integer[][]b){
		Integer a1 = a.length;
		Integer a2 = a[0].length;
		Integer b1 = b.length;
		Integer b2 = b[0].length;
		Integer[][] c = new Integer[a1][a2];
		if(a1!=b1||a2!=b2){
			System.out.println("两个矩阵的行数或者列数不相等，不能进行相减");
		}
		for(Integer i=0;i<a1;i++){
			for(Integer j=0;j<a2;j++){
				c[i][j] = a[i][j]-b[i][j];
			}
		}
		return c;
	}

	/**
	 * 采用传统的矩阵相乘的算法,此函数也会作为其余两个方法的调用函数
	 *
	 * @param a
	 * 这个a指的是局部方法里面的a
	 * @param b
	 * 这个b指的是局部方法的b
	 * @return
	 */
	private Integer[][] NormalCal(Integer[][] a, Integer[][] b) {
		Integer lena1 = a.length;// 得到a的行数
		Integer lena2 = a[0].length;// 得到a的列数
		Integer lenb2 = b[0].length;// 得到b的列数
		Integer[][] c = new Integer[a.length][b[0].length];
		for (Integer i = 0; i < lena1; i++) {
			for (Integer j = 0; j < lenb2; j++) {
				Integer sum = 0;
				for (Integer k = 0; k < lena2; k++) {
					sum += a[i][k] * b[k][j];
				}
				c[i][j] = sum;
			}
		}
		return c;
	}

	/**
	 * 这里采用strassen算法，strassen算法和普通的分治法实现在前面都是一样的，进行划分，
	 * 主要是后面的做法，只进行了7次乘法同事进行了10次加减法
	 * @param aa
	 * @param bb
	 * @return
	 */
	private Integer[][] Strassen(Integer[][] aa,Integer[][]bb){
		Integer lenaa1 = aa.length;// 得到a的行数
		Integer lenaa2 = aa[0].length;// 得到a的列数
		Integer lenbb1 = bb.length;// 这个值应该和a的列数一样,只是保留在这里不用
		Integer lenbb2 = bb[0].length;// 得到b的列数
		Integer[][] cc = new Integer[lenaa1][lenbb2];
		//如果aa的行数或者aa的列数（等于bb的行数）或者bb的列数为1，即停止进行分解。
		if(lenaa1==1||lenaa2==1||lenbb2==1){
			cc = NormalCal(aa, bb);
			return cc;
		}
		if(lenaa1!=lenaa2||lenaa1!=lenbb1||lenbb1!=lenbb2||lenaa1%2!=0){
			System.out.println("不能进行strassen算法，strassen算法只能是两个矩阵都是方阵"
					+ "且其行数是2的幂");
			return cc;
		}
		//构造最后得到的cc数组的四个分块矩阵
		Integer c111 = lenaa1/2;
		Integer c112 = lenbb2/2;
		Integer c121 = lenaa1/2;
		Integer c122 = (lenbb2+1)/2;
		Integer c211 = (lenaa1+1)/2;
		Integer c212 = lenbb2/2;
		Integer c221 = (lenaa1+1)/2;
		Integer c222 = (lenbb2+1)/2;
		Integer[][] c11 = new Integer[c111][c112];
		Integer[][] c12 = new Integer[c121][c122];
		Integer[][] c21 = new Integer[c211][c212];
		Integer[][] c22 = new Integer[c221][c222];
		//一下四个数组分别存放aa的划分的四个部分lena111表示aa第一个分块矩阵a11的行数
		Integer lena111 = lenaa1/2;//记录aa矩阵分块的第一个矩阵的行数
		Integer lena112 = lenaa2/2;//记录aa矩阵分块的第一个矩阵的列数
		Integer lena121 = lenaa1/2;//以下类似
		Integer lena122 = (lenaa2+1)/2;
		Integer lena211 = (lenaa1+1)/2;
		Integer lena212 = lenaa2/2;
		Integer lena221 = (lenaa1+1)/2;
		Integer lena222 = (lenaa2+1)/2;
		Integer[][] a11 = new Integer[lenaa1/2][lenaa2/2];
		Integer[][] a12 = new Integer[lenaa1/2][(lenaa2+1)/2];
		Integer[][] a21 = new Integer[(lenaa1+1)/2][lenaa2/2];
		Integer[][] a22 = new Integer[(lenaa1+1)/2][(lenaa2+1)/2];
		//进行初始化操作
		Integer i=0;
		Integer j=0;
		for(i=0;i<lena111;i++){
			for(j=0;j<lena112;j++){
				a11[i][j] = aa[i][j];
			}
		}
		for(i=0;i<lena121;i++){
			for(j=0;j<lena122;j++){
				a12[i][j] = aa[i][j+lena112];
			}
		}
		for(i=0;i<lena211;i++){
			for(j=0;j<lena212;j++){
				a21[i][j] = aa[i+lena111][j];
			}
		}
		for(i=0;i<lena221;i++){
			for(j=0;j<lena222;j++){
				a22[i][j] = aa[i+lena111][j+lena112];
			}
		}
		//以下四个数组分别存放bb划分的四个部分
		Integer lenb111 = lenbb1/2;//记录aa矩阵分块的第一个矩阵的行数
		Integer lenb112 = lenbb2/2;//记录aa矩阵分块的第一个矩阵的列数
		Integer lenb121 = lenbb1/2;//以下类似
		Integer lenb122 = (lenbb2+1)/2;
		Integer lenb211 = (lenbb1+1)/2;
		Integer lenb212 = lenbb2/2;
		Integer lenb221 = (lenbb1+1)/2;
		Integer lenb222 = (lenbb2+1)/2;
		Integer[][] b11 = new Integer[lenb111][lenb112];
		Integer[][] b12 = new Integer[lenb121][lenb122];
		Integer[][] b21 = new Integer[lenb211][lenb212];
		Integer[][] b22 = new Integer[lenb221][lenb222];
		for(i=0;i<lenb111;i++){
			for(j=0;j<lenb112;j++){
				b11[i][j] = bb[i][j];
			}
		}
		for(i=0;i<lenb121;i++){
			for(j=0;j<lenb122;j++){
				b12[i][j] = bb[i][j+lenb112];
			}
		}
		for(i=0;i<lenb211;i++){
			for(j=0;j<lenb212;j++){
				b21[i][j] = bb[i+lenb111][j];
			}
		}
		for(i=0;i<lenb221;i++){
			for(j=0;j<lenb222;j++){
				b22[i][j] = bb[i+lenb111][j+lenb112];
			}
		}
		//下面开始strassen的递归调用
		//首先构造10个矩阵s1~s10
		Integer[][] s1 = MatrixMinus(b12, b22);
		Integer[][] s2 = MatrixAdd(a11, a12);
		Integer[][] s3 = MatrixAdd(a21,a22);
		Integer[][] s4 = MatrixMinus(b21, b11);
		Integer[][] s5 = MatrixAdd(a11, a22);
		Integer[][] s6 = MatrixAdd(b11, b22);
		Integer[][] s7 = MatrixMinus(a12, a22);
		Integer[][] s8 = MatrixAdd(b21, b22);
		Integer[][] s9 = MatrixMinus(a11, a21);
		Integer[][] s10 = MatrixAdd(b11, b12);
		//进行七次递归相乘
		Integer[][] p1 = Strassen(a11, s1);
		Integer[][] p2 = Strassen(s2, b22);
		Integer[][] p3 = Strassen(s3, b11);
		Integer[][] p4 = Strassen(a22, s4);
		Integer[][] p5 = Strassen(s5, s6);
		Integer[][] p6 = Strassen(s7, s8);
		Integer[][] p7 = Strassen(s9, s10);
		c11 = MatrixAdd(MatrixMinus(MatrixAdd(p5, p4), p2),p6);
		c12 = MatrixAdd(p1, p2);
		c21 = MatrixAdd(p3, p4);
		c22 = MatrixMinus(MatrixAdd(p5, p1), MatrixAdd(p3, p7));
		for(i=0;i<c111;i++){
			for(j=0;j<c112;j++){
				cc[i][j] = c11[i][j];
			}
		}
		for(i=0;i<c121;i++){
			for(j=0;j<c122;j++){
				cc[i][j+c112] = c12[i][j];
			}
		}
		for(i=0;i<c211;i++){
			for(j=0;j<c212;j++){
				cc[i+c111][j] = c21[i][j];
			}
		}
		for(i=0;i<c221;i++){
			for(j=0;j<c222;j++){
				cc[i+c111][j+c112] = c22[i][j];
			}
		}
		return cc;
	}

	// 将得到的c进行输出
	public void print() {
		//这是调用普通的算法
		System.out.println("这是调用普通的算法");
		this.c = NormalCal(this.a, this.b);
		printMatrix(c);
		System.out.println("这里调用strassen算法");
		this.cds = Strassen(this.a, this.b);
		printMatrix(cds);
	}

	public void printMatrix(Integer[][] c) {
		for (Integer i = 0; i < c.length; i++) {
			for (Integer j = 0; j < c[0].length; j++) {
				System.out.print(c[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		System.out.println("本程序主要用于演示stassen算法");
		Integer[][] a = new Integer[][] { { 1, 0, 1 }, { 0, 0, 1 },
				{ 1, 0, 0 }, { 1, 1, 1 }, { 0, 1, 0 } };
		Integer[][] b = new Integer[][] { { 1, 0, 1, 0, 1, 1, 0 },
				{ 0, 1, 1, 0, 1, 0, 1 }, { 1, 0, 1, 1, 0, 0, 1 } };
		if (a[0].length != b.length) {
			System.out.println("a的行数与b的列数不一致，不能进行计算");
			return;
		}// 如果a行数与b的列数不一致，那么直接返回不能进行计算
		// 调用常规的函数进行计算
		Strassen matrixmultiple = new Strassen(a, a);
		matrixmultiple.print();
	}

}
