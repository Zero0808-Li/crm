如何使用jquery的日期插件
  * 引入相关的文件
      * jquery的js类库
         <script type="text/javascript" src="../../js/jquery-1.4.2.js"></script>
      * 引入插件的类库
        <script type="text/javascript" src="./jquery.datepick.js"></script>   
      *  引入css修饰的文件
         <link rel="stylesheet" href="./jquery.datepick.css" type="text/css">
      *  引入汉化包
          <script type="text/javascript" src="./jquery.datepick-zh-CN.js"></script>   
          
   * 在html页面使用
       * 在页面增加   设置class属性
            <input type="text" name="time" value="2009-2-9" class="biuuu" />
			<input type="text" name="time" value="2009-2-9" class="biuuu" />
			<input type="text" name="time" value="2009-2-9" class="biuuu" />
			          
	    * 增加如下代码
	        $(document).ready(function(){
	            //设置显示的样式和显示的位置
	            $('.biuuu').datepick({dateFormat: 'yy/mm/dd'}); 
            });
            
            
     * 注: calendar.js自定义的文件,把  
             $(document).ready(function(){
	            //设置显示的样式和显示的位置
	            $('.biuuu').datepick({dateFormat: 'yy/mm/dd'}); 
            });
            
                      代码写入到该文件中   
	        
	    		          
			          