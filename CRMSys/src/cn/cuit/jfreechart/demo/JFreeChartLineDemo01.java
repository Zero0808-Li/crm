package cn.cuit.jfreechart.demo;

import java.awt.Font;
import java.awt.Rectangle;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：折线图  */
public class JFreeChartLineDemo01 {
	public static void main(String[] args) {
		CategoryDataset dataset = getDataset();
		JFreeChart chart = ChartFactory.createLineChart("客户分类统计报表（客户等级）", 			 // * 主标题
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
		CategoryAxis categoryAxis = categoryPlot.getDomainAxis();
		
		// * 获取Y轴
		NumberAxis numberAxis = (NumberAxis) categoryPlot.getRangeAxis();
		
		// * 获取绘图对象
		LineAndShapeRenderer lineAndShapeRenderer = (LineAndShapeRenderer) categoryPlot.getRenderer();
		
		/***********************************************************************************************************************************/
		
		/* 处理乱码 */
		/* 1：处理主标题乱码 */
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 18));
		/* 2：处理子标题乱码 */
		chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 18));
		
		/***********************************************************************************************************************************/

		/* 3：处理X轴乱码 */
		categoryAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 15));  // * 轴上
		categoryAxis.setLabelFont(new Font("宋体", Font.BOLD, 15));		  // * 轴外
		
		/* 4：处理Y轴乱码 */
		numberAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 15));  // * 轴上
		numberAxis.setLabelFont(new Font("宋体", Font.BOLD, 15));	    // * 轴外
		
		/***********************************************************************************************************************************/

		/* 5：处理Y轴的刻度 */
		// * 设置Y轴不是自动刻度
		numberAxis.setAutoRange(false);
		// * 设置刻度
		NumberTickUnit numberTickUnit = new NumberTickUnit(1);
		numberAxis.setTickUnit(numberTickUnit);
		
		/***********************************************************************************************************************************/
		
		/* 6：处理拐角处的点(为矩形)*/
		Rectangle rectangle = new Rectangle(8, 8);
		lineAndShapeRenderer.setSeriesShape(0, rectangle);
		lineAndShapeRenderer.setSeriesShapesVisible(0, true);
		
		/* 7：处理上线形图的数字*/
		lineAndShapeRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		
		// * 设置线形图上的数字可见
		lineAndShapeRenderer.setBaseItemLabelsVisible(true);
		
		// * 设置线形图上的字体
		lineAndShapeRenderer.setBaseItemLabelFont(new Font("宋体", Font.BOLD, 15));
		
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
		dataset.addValue(4, "客户等级", "重要客户");
		dataset.addValue(3, "客户等级", "潜在客户");
		return dataset;
	}
}
