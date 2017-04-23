package org.javacream.training.java.experts.annotations.apt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LombokPerson {

	private String name;
	private int height;
}
