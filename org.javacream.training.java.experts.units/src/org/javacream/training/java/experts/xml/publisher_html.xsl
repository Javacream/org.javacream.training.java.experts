<?xml version="1.0" encoding="ISO-8859-1"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
	<xsl:output method="html" />

	<xsl:template match="/">
		<html>
			<body>
				<h1>PUBLISHING</h1>
				<h2><xsl:value-of select="publisher/@name"></xsl:value-of></h2>
				
				<table border="1">
					<tr bgcolor="#9acd32">
						<th>ISBN</th>
						<th>TITLE</th>
						<th>PRICE</th>
					</tr>
					<xsl:for-each select="publisher/book">
						<tr>
							<td>
								<xsl:value-of select="isbn" />
							</td>
							<td>
								<xsl:value-of select="title" />
							</td>
							<td>
								<xsl:value-of select="price" />
							</td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>


