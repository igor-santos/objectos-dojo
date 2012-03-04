---
layout: section
title: "Caixa de ferramentas"
---
<div class="row">
  <div class="span4">
    <h3>Processos</h3>
    
    {% assign section_list = "processo" %}
    {% include obj/section_list %}
        
  </div>
  <div class="span4">
    <h3>Git</h3>
    
    {% assign section_list = "git" %}
    {% include obj/section_list %}
         
  </div>
  <div class="span4">
    <h3></h3>
  </div>
</div>

<div class="span10">

  <h3>Aprendizes <small>katas faixa branca</small></h3>
  <p>Treinamento b&aacute;sico de todos os aprendizes objectos.</p>
  <ul>
  {% for post in site.tags.aprendizes %}
    <li><a href="{{ post.url }}">{{ post.title }}</a></li>
  {% endfor %}
  </ul>

  <h3>Git</h3>
  <ul>
  {% for post in site.tags.git %}
    <li><a href="{{ post.url }}">{{ post.title }}</a></li>
  {% endfor %}
  </ul>
  
</div>