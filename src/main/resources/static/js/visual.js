// 饼形图
$.ajax({
    type:"post",
    url:"http://localhost:8080/getEcharts",
    success:function (da) {
        (function() {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.querySelector(".pie .chart"));
            // 准备数据
            var data = {
                time: da.times
            };
            option = {
                tooltip: {
                    trigger: "item",
                    formatter: "{a} <br/>{b}: {c} ({d}%)",
                    position: function(p) {
                        //其中p为当前鼠标的位置
                        return [p[0] + 10, p[1] - 10];
                    }
                },
                legend: {
                    top: "90%",
                    itemWidth: 10,
                    itemHeight: 10,
                    data: ["0-6时段","6-12时段","12-18时段","18-24时段"],
                    textStyle: {
                        color: "rgba(255,255,255,.5)",
                        fontSize: "12"
                    }
                },
                series: [
                    {
                        name: "时段分布",
                        type: "pie",
                        center: ["50%", "42%"],
                        radius: ["35%", "60%"],
                        color: [
                            "#065aab",
                            "#066eab",
                            "#0682ab",
                            "#0696ab"
                        ],
                        label: { show: false },
                        labelLine: { show: false },
                        data: [
                            { value: data.time[0], name: "0-6时段" },
                            { value: data.time[1], name: "6-12时段" },
                            { value: data.time[2], name: "12-18时段" },
                            { value: data.time[3], name: "18-24时段" }
                        ]
                    }
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            window.addEventListener("resize", function() {
                myChart.resize();
            });
        })();

        //折线图
        (function() {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.querySelector(".line .chart"));
            //准备数据
            var data = {
                month: da.mouths
            };
            // 2. 指定配置和数据
            var option = {
                color: ["#00f2f1", "#ed3f35"],
                tooltip: {
                    trigger: "axis"
                },
                legend: {
                    right: "10%",
                    textStyle: {
                        color: "#4c9bfd"
                    }
                },
                grid: {
                    top: "20%",
                    left: "3%",
                    right: "4%",
                    bottom: "3%",
                    show: true,
                    borderColor: "#012f4a",
                    containLabel: true
                },
                xAxis: {
                    type: "category",
                    boundaryGap: false,
                    data: [
                        "1月", "2月", "3月", "4月",
                        "5月", "6月", "7月", "8月",
                        "9月", "10月", "11月", "12月"
                    ],
                    axisTick: {
                        show: false
                    },
                    axisLabel: {
                        color: "rgba(255,255,255,.7)"
                    },
                    axisLine: {
                        show: false
                    }
                },
                yAxis: {
                    type: "value",
                    axisTick: {
                        show: false
                    },
                    axisLabel: {
                        color: "rgba(255,255,255,.7)"
                    },
                    splitLine: {
                        lineStyle: {
                            color: "#012f4a"
                        }
                    }
                },
                series: [
                    {
                        name: "访问次数",
                        type: "line",
                        smooth: true,
                        data: data.month
                    }
                ]
            };
            // 3. 把配置和数据给实例对象
            myChart.setOption(option);
            window.addEventListener("resize", function() {
                myChart.resize();
            });
        })();

        // 柱状图
        (function() {
            // 实例化对象
            var myChart = echarts.init(document.querySelector(".bar .chart"));
            // 准备数据
            var data = {
                province: da.provinces
            };
            console.log(data);
            // 指定配置和数据
            var option = {
                color: ["#2f89cf"],
                tooltip: {
                    trigger: "axis",
                    axisPointer: {
                        // 坐标轴指示器，坐标轴触发有效
                        type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                grid: {
                    left: "0%",
                    top: "10px",
                    right: "0%",
                    bottom: "4%",
                    containLabel: true
                },
                xAxis: [
                    {
                        type: "category",
                        data: [
                            "北京", "天津", "上海", "重庆", "新疆", "西藏", "宁夏", "内蒙古",
                            "广西", "黑龙江", "吉林", "辽宁", "河北", "山东", "江苏", "安徽",
                            "浙江", "福建", "广东", "海南", "云南", "贵州", "四川", "湖南",
                            "湖北", "河南", "山西", "陕西", "甘肃", "青海", "江西", "台湾", "香港", "澳门"
                        ],
                        axisTick: {
                            alignWithLabel: true
                        },
                        axisLabel: {
                            textStyle: {
                                color: "rgba(255,255,255,.6)",
                                fontSize: "10"
                            }
                        },
                        axisLine: {
                            show: false
                        }
                    }
                ],
                yAxis: [
                    {
                        type: "value",
                        axisLabel: {
                            textStyle: {
                                color: "rgba(255,255,255,.6)",
                                fontSize: "12"
                            }
                        },
                        axisLine: {
                            lineStyle: {
                                color: "rgba(255,255,255,.1)"
                            }
                        },
                        splitLine: {
                            lineStyle: {
                                color: "rgba(255,255,255,.1)"
                            }
                        }
                    }
                ],
                series: [
                    {
                        name: "访问人数",
                        type: "bar",
                        barWidth: "35%",
                        data: data.province,
                        itemStyle: {
                            barBorderRadius: 4
                        }
                    }
                ]
            };

            // 把配置给实例对象
            myChart.setOption(option);
            window.addEventListener("resize", function() {
                myChart.resize();
            });

            $(".bar h2 ").on("click", "a", function() {
                option.series[0].data = dataAll[$(this).index()].data;
                myChart.setOption(option);
            });
        })();
    }
});





