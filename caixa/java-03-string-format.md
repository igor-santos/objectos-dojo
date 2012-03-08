---
layout: post-alpha
title: Método String.format
author: "Tiago Aguiar"
published: true
partof: java
num: 3
outof: 3
---

## Introdução

O método _format_ da classe [java.lang.String] (http://docs.oracle.com/javase/7/docs/api/java/lang/String.html)
tem por finalidade retornar um conjunto de caracteres 
formatados. Essa formatação dependerá de uma _String_ especificadora e/ou um objeto _Locale_
passados através de argumentos.

Temos uma sobrecarga do método format:

1. public static String format(String format, Object... args)
2. public static String format(Locale l, String format, Object... args)

## public static String format(String format, Object... args)

Começaremos com um exemplo:




    