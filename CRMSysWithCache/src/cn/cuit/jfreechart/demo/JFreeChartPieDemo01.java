package cn.cuit.jfreechart.demo;

import java.awt.Font;
import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;


/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：  JFreeChart示例（JFreeChart版本的版本不同兼容性较差）*/
public class JFreeChartPieDemo01 {
	public static void main(String[] args) throws IOException {
		PieDataset dataset = getDataset();
		JFreeChart chart = ChartFactory.createPieChart3D("客户分类统计报表（客户等级）", 			 // * 主标题
														 dataset, 						 // * 图表所需的数据
														 true, 							 // * 是否显示图例
														 true, 							 // * 是否显示工具提示
														 true);							 // * 是否产生URL链接

		// * 获取图表区域对象
		PiePlot3D piePlot3D = (PiePlot3D) chart.getPlot();
		
		/***********************************************************************************************************************************/
		
		/* 处理乱码 */
		/* 1：处理主标题乱码 */
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 18));
		/* 2：处理子标题乱码 */
		chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 18));
		
		/***********************************************************************************************************************************/
		
		/* 处理饼图的标题乱码 */
		piePlot3D.setLabelFont(new Font("宋体", Font.BOLD, 15));
		
		/***********************************************************************************************************************************/

		/* 处理饼图显示的信息 */
		String labelFormat = "{0}:{1}({2})";  
		/* 以
				dataset.setValue("机会客户", 3);
				dataset.setValue("签约客户", 2);
				dataset.setValue("重要客户", 2);
				dataset.setValue("潜在客户", 4);
		*/
		// * {0}表示:机会客户
		// * {1}表示:3
		// * {2}表示:所占的百分比
		piePlot3D.setLabelGenerator(new StandardPieSectionLabelGenerator(labelFormat));
		
		
		/***********************************************************************************************************************************/
		
		/* 生成图片 */
		File file = new File("D:/a.jpg");
		ChartUtilities.saveChartAsJPEG(file, chart, 600, 400);
		
		/***********************************************************************************************************************************/
		ChartFrame frame = new ChartFrame("XXX", chart);
		frame.setVisible(true);
		frame.pack();
		/***********************************************************************************************************************************/
		
	}

	/**
	 * @return
	 */
	private static PieDataset getDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("机会客户", 3);
		dataset.setValue("签约客户", 2);
		dataset.setValue("重要客户", 2);
		dataset.setValue("潜在客户", 4);
		return dataset;
	}
}
