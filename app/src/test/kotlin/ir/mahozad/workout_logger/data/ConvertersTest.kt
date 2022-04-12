package ir.mahozad.workout_logger.data

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ConvertersTest {
    @Test fun `Convert lowercase male string to Sex`() {
        val converter = SexConverter()
        val sex = converter.convertToSex("male")
        assertThat(sex).isEqualTo(Sex.MALE)
    }

    @Test fun `Convert uppercase MALE string to Sex`() {
        val converter = SexConverter()
        val sex = converter.convertToSex("MALE")
        assertThat(sex).isEqualTo(Sex.MALE)
    }

    @Test fun `Convert mixed case MaLE string to Sex`() {
        val converter = SexConverter()
        val sex = converter.convertToSex("MaLE")
        assertThat(sex).isEqualTo(Sex.MALE)
    }

    @Test fun `Convert lowercase female string to Sex`() {
        val converter = SexConverter()
        val sex = converter.convertToSex("female")
        assertThat(sex).isEqualTo(Sex.FEMALE)
    }

    @Test fun `Convert uppercase FEMALE string to Sex`() {
        val converter = SexConverter()
        val sex = converter.convertToSex("FEMALE")
        assertThat(sex).isEqualTo(Sex.FEMALE)
    }

    @Test fun `Convert mixed case feMaLE string to Sex`() {
        val converter = SexConverter()
        val sex = converter.convertToSex("feMaLE")
        assertThat(sex).isEqualTo(Sex.FEMALE)
    }

    @Test fun `Convert lowercase other string to Sex`() {
        val converter = SexConverter()
        val sex = converter.convertToSex("other")
        assertThat(sex).isEqualTo(Sex.OTHER)
    }

    @Test fun `Convert uppercase OTHER string to Sex`() {
        val converter = SexConverter()
        val sex = converter.convertToSex("OTHER")
        assertThat(sex).isEqualTo(Sex.OTHER)
    }

    @Test fun `Convert mixed case oTHer string to Sex`() {
        val converter = SexConverter()
        val sex = converter.convertToSex("oTHer")
        assertThat(sex).isEqualTo(Sex.OTHER)
    }

    @Test fun `Convert MALE Sex to string`() {
        val converter = SexConverter()
        val sex = converter.convertToString(Sex.MALE)
        assertThat(sex).isEqualTo("Male")
    }

    @Test fun `Convert FEMALE Sex to string`() {
        val converter = SexConverter()
        val sex = converter.convertToString(Sex.FEMALE)
        assertThat(sex).isEqualTo("Female")
    }

    @Test fun `Convert OTHER Sex to string`() {
        val converter = SexConverter()
        val sex = converter.convertToString(Sex.OTHER)
        assertThat(sex).isEqualTo("Other")
    }
}
