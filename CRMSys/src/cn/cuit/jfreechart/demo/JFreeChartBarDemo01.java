package cn.cuit.jfreechart.demo;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis3D;
import org.jfree.chart.axis.NumberAxis3D;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：  JFreeChart示例（JFreeChart版本的版本不同兼容性较差），查看API文档和官方提供的demo.jar文件*/
public class JFreeChartBarDemo01 {
	public static void main(String[] args) {
		CategoryDataset dataset = getDataset();
		JFreeChart chart = ChartFactory.createBarChart3D("客户分类统计报表（客户等级）", 			 // * 主标题
														 "客户等级",  						 // * X轴标题（横轴）
														 "数量",							 // * Y轴标题（纵轴）
														 dataset, 						 // * 图表所需的数据
														 PlotOrientation.VERTICAL,  	 // * 图表的方向（水平和垂直），这里为水平
														 true, 							 // * 是否显示图例
														 true, 							 // * 是否显示工具提示
														 true);							 // * 是否产生URL链接

		// * 获取图表区域对象
		CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot();
		
		// * 获取X轴
		CategoryAxis3D categoryAxis3D = (CategoryAxis3D) categoryPlot.getDomainAxis();
		
		// * 获取Y轴
		NumberAxis3D numberAxis3D = (NumberAxis3D) categoryPlot.getRangeAxis();
		
		// * 获取绘图对象
		BarRenderer3D barRenderer3D = (BarRenderer3D) categoryPlot.getRenderer();
		
		/***********************************************************************************************************************************/
		
		/* 处理乱码 */
		/* 1：处理主标题乱码 */
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 18));
		/* 2：处理子标题乱码 */
		chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 18));
		
		/***********************************************************************************************************************************/

		/* 3：处理X轴乱码 */
		categoryAxis3D.setTickLabelFont(new Font("宋体", Font.BOLD, 15));  // * 轴上
		categoryAxis3D.setLabelFont(new Font("宋体", Font.BOLD, 15));		  // * 轴外
		
		/* 4：处理Y轴乱码 */
		numberAxis3D.setTickLabelFont(new Font("宋体", Font.BOLD, 15));  // * 轴上
		numberAxis3D.setLabelFont(new Font("宋体", Font.BOLD, 15));	    // * 轴外
		
		/***********************************************************************************************************************************/

		/* 5：处理Y轴的刻度 */
		// * 设置Y轴不是自动刻度
		numberAxis3D.setAutoRange(false);
		// * 设置刻度
		NumberTickUnit numberTickUnit = new NumberTickUnit(1);
		numberAxis3D.setTickUnit(numberTickUnit);
		
		/***********************************************************************************************************************************/
		
		/* 6：处理图像宽度（胖瘦） */
		barRenderer3D.setMaximumBarWidth(0.08);
		
		/* 7：处理柱体上的数字*/
		barRenderer3D.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		
		// * 设置柱体上的数字可见
		barRenderer3D.setBaseItemLabelsVisible(true);
		
		// * 设置柱体上的字体
		barRenderer3D.setBaseItemLabelFont(new Font("宋体", Font.BOLD, 15));
		
		/***********************************************************************************************************************************/
		ChartFrame frame = new ChartFrame("XXX", chart);
		frame.setVisible(true);
		frame.pack();
		/***********************************************************************************************************************************/
		
	}

	/**
	 * @return
	 */
	private static CategoryDataset getDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(3, "客户等级", "机会客户");
		dataset.addValue(1, "客户等级", "签约客户");
		dataset.addValue(2, "客户等级", "重要客户");
		dataset.addValue(3, "客户等级", "潜在客户");
		return dataset;
	}
}
