<#assign MyJavaObject = "com.snake.book.freemarker.BookSourceMethodModelEx"?new()>
<#assign book_type_list = MyJavaObject("book_type_list")>
<#assign book_element_list = MyJavaObject("book_element_list")>
<#assign book_structure_list = MyJavaObject("book_structure_list")>
<html>
<#include "/head.ftl"/>
<body>
<div class="am-cf admin-main">
        <!-- content start -->
        <div class="admin-content am-center am-padding">
            <div class="am-cf">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">短语/句子</strong> / <small>分析</small></div>
            </div>

            <div class="am-center">
                <#if wordList??>
                <div class="am-center am-pagination">
                    <span class="am-input-group-btn">
                        <#list wordList as each>
                            <button class="am-btn am-btn-success" onclick="appendWord('${each.word}')">${each.word}</button>
                        </#list>
                    </span>
                </div>
                <div class="am-pagination">
                    <div class="am-input-group am-input-group-sm am-u-sm-12 am-u-md-8">
                    <input id="input_add_word" type="text" class="am-form-field">
                    <span class="am-input-group-btn">
                    <button class="am-btn am-btn-primary" onclick="clearNewWord()">清空输入框</button>
                        </span>
                    </div>
                </div>
                <div class="am-form-group">
                    <span class="am-input-group-btn">
                    </span>
                    <#list book_type_list as each>
                        <button class="am-btn am-btn-default" onclick="selectWordType(#{each.id})">${each.name}</button>
                    </#list>
                </div>
                <div class="am-pagination am-u-sm-12 am-u-md-8">
                    <span class="am-input-group-btn">
                        <button class="am-btn am-btn-secondary" onclick="addWord()">添加词组</button>
                        <button class="am-btn am-btn-primary" onclick="savePhraseWord()">保存现有词组划分</button>
                    </span>
                </div>
                </#if>
                <#if phraseWordList??>
                    <div class="am-center am-pagination">
                    <span class="am-input-group-btn">
                        <#list phraseWordList as each>
                            <button class="am-btn am-btn-success" onclick="loadWord(${each.wordId},'${each.typeId}','${each.word.word}')">${each.word.word}</button>
                        </#list>
                    </span>
                    </div>


                <div class="am-form-group">
                    <label>语法结构</label>
                <#list book_structure_list as each>
                    <span class="am-input-group-btn">
                        <button class="am-btn am-btn-<#if each.id==obj.structureId>secondary<#else>default</#if> book_structure book_structure_id${each.id}" onclick="selectStructure(${each.id})">${each.name}</button>
                        <#--<#list each.values as c>
                            <button class="am-btn am-btn-secondary">${c}</button>
                        </#list>-->
                        </span>
                </#list>
                </div>

                <div class="am-form-group">
                    <label>语句成分</label>
                    <span class="am-input-group-btn"></span>
                <#list book_element_list as each>
                    <button class="am-btn am-btn-default book_element_id${each.id} book_element">${each.name}</button>
                </#list>
                </div>

                <div class="am-form-group">
                    <label>词语类型</label>
                    <span class="am-input-group-btn"></span>
                <#list book_type_list as each>
                    <#--<#if each_index%10==0>
                    <span class="am-input-group-btn">
                    </#if>-->
                    <button class="am-btn am-btn-default book_type_id${each.id} book_type" onclick="selectWordType(#{each.id})">${each.name}</button>
                    <#--<#if each_index%10==9 || !each_has_next>
                    </span>
                    </#if>-->
                </#list>
                </div>
                </#if>
            </div>
        </div>
        <!-- content end -->
</div>
<script type="text/javascript">
<#if wordList??>
var selectedTypeId = null;
    function clearNewWord(){
        $("#input_add_word").val("");
    }
    function appendWord(value){
        //part1
        $("#input_add_word").val($("#input_add_word").val()+value);
    }
    function selectWordType(typeId){
        selectedTypeId = typeId;
    }
    function addWord(){
        if(selectedTypeId){
            var word = $("#input_add_word").val();
            if(word && word != "" && word.length > 0){
                $.post("${request.contextPath}/book/word/addWord",{word:word,typeId:selectedTypeId},function(result){
                    if("success" == result){
                        window.location.reload();
                    }
                });
            }else{
                alert("select word");
            }
        }else{
            alert("select type");
        }
    }
    function savePhraseWord(){
        $.post("${request.contextPath}/book/phrase/savePhraseWord",{id:${obj.id}},function(result){
            if("success" == result){
                window.location.reload();
            }
        });
    }
</#if>
<#if phraseWordList??>
var selectedWordId = null;
    function selectWordType(typeId){
        if(selectedWordId){
            var type = $(".book_type_id"+typeId);
            if(type.hasClass("am-btn-secondary")){
                if(confirm("确认移除此类型？")){
                    $.post("${request.contextPath}/book/word/removeType",{id:selectedWordId,typeId:typeId},function(result){
                        if("success" == result){
                            removeWordTypeSelected(typeId);
                        }
                    });
                }
            }else{
                if(confirm("确认添加此类型？")){
                    $.post("${request.contextPath}/book/word/addType",{id:selectedWordId,typeId:typeId},function(result){
                        if("success" == result){
                            wordTypeSelected(typeId);
                        }
                    });
                }
            }
        }
    }
    function selectStructure(structureId){
        var structure = $(".book_structure_id"+structureId);
        if(structure.hasClass("am-btn-default") && confirm("确认更新语句结构？")){
            $.post("${request.contextPath}/book/phrase/updateStructureId",{id:${obj.id},structureId:structureId},function(result){
                if("success" == result){
                    $("button.book_structure").removeClass("am-btn-secondary").add("am-btn-default");
                    structure.removeClass("am-btn-default").addClass("am-btn-secondary");
                }
            });
        }
    }
    function removeWordTypeSelected(typeId){
        if(typeId){
            $("button.book_type_id"+typeId).removeClass("am-btn-secondary").addClass("am-btn-default");
        }else{
            $("button.book_type.am-btn-secondary").removeClass("am-btn-secondary").addClass("am-btn-default");
        }
    }
    function wordTypeSelected(typeId){
        $("button.book_type_id"+typeId).removeClass("am-btn-default").addClass("am-btn-secondary");
    }
    function loadWord(id,typeId,value){
        //part0
        selectedWordId = id;
        //part2
        if(typeId != ''){
            wordTypeSelected(typeId);
        }
        removeWordTypeSelected();
        $.post("${request.contextPath}/book/word/loadWordTypeList",{id:id}, function (result) {
            if(result && result instanceof Array){
                for(var i in result){
                    var typeId = result[i].typeId;
                    wordTypeSelected(typeId);
                }
            }
        });
    }
</#if>
</script>
</body>
</html>
