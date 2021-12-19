package sh.gcj.aoc.day16;

import com.google.common.base.Joiner;
import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day16 extends Day<Character> {

    public Day16() {
        super(16);
    }

    @Override
    public List<Character> parseInput(Stream<String> input) {
        var hex = input.collect(Collectors.toList()).get(0);
        return padZeros(new BigInteger(hex, 16).toString(2).chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.toList()));
    }

    @Override
    public Integer solvePart1(List<Character> input) throws NoSolutionException {
        var reader = new BitReader(input);
        var packet = Packet.parse(reader);

        return packet.sumVersions();
    }

    @Override
    public Long solvePart2(List<Character> input) throws NoSolutionException {
        var reader = new BitReader(input);
        var packet = Packet.parse(reader);

        return packet.value();
    }

    private static class Packet {
        private enum Type {
            SUM(0),
            PRODUCT(1),
            MINIMUM(2),
            MAXIMUM(3),
            LITERAL(4),
            GREATER_THAN(5),
            LESS_THAN(6),
            EQUAL_TO(7);

            private final int value;

            Type(int value) {
                this.value = value;
            }

            public static Type from(int value) {
                for (var type : values()) {
                   if (type.value == value)  {
                       return type;
                   }
                }
                throw new IllegalArgumentException();
            }
        }

        List<Packet> subpackets;
        Integer version;
        Type type;
        Long value;

        public Packet(Integer version, Type type, List<Packet> subpackets, Long value) {
            this.version = version;
            this.type = type;
            this.subpackets = subpackets;
            this.value = value;
        }

        public int sumVersions() {
            var versionSums = version;
            for (var subpacket : subpackets) {
                versionSums += subpacket.sumVersions();
            }
            return versionSums;
        }

        public Long value() {
            return switch (type) {
                case SUM -> subpackets.stream().map(Packet::value).reduce(0L, Long::sum);
                case PRODUCT -> subpackets.stream().map(Packet::value).reduce(1L, (a, b) -> a * b);
                case MINIMUM -> subpackets.stream().map(Packet::value).reduce(Long.MAX_VALUE, Math::min);
                case MAXIMUM -> subpackets.stream().map(Packet::value).reduce(Long.MIN_VALUE, Math::max);
                case LITERAL -> value;
                case GREATER_THAN -> subpackets.get(0).value() > subpackets.get(1).value() ? 1L : 0L;
                case LESS_THAN -> subpackets.get(0).value() < subpackets.get(1).value() ? 1L : 0L;
                case EQUAL_TO -> subpackets.get(0).value().equals(subpackets.get(1).value()) ? 1L : 0L;
            };
        }

        public static Packet parse(BitReader reader) {
            // Get the version and type from the packet header (first 6 bits)
            var version = Integer.parseInt(Joiner.on("").join(reader.read(3)), 2);
            var type = Type.from(Integer.parseInt(Joiner.on("").join(reader.read(3)), 2));

            if (type == Type.LITERAL) {
                return parseLiteralPacket(reader, version);
            }

            return parseOperatorPacket(reader, version, type);
        }

        private static Packet parseLiteralPacket(BitReader reader, Integer version) {
            var bitString = new StringBuilder();
            List<Character> bits;

            // Read 4 bits at a time to build up the value of this packet, until the prefix bit is a 0 (that signals
            // the end)
            do {
                bits = reader.read(5);
                bits.subList(1, 5).forEach(bitString::append);
            } while (bits.get(0) != '0');

            var num = Long.parseLong(bitString.toString(), 2);
            return new Packet(version, Type.LITERAL, List.of(), num);
        }

        private static Packet parseOperatorPacket(BitReader reader, Integer version, Type type) {
            List<Packet> subpackets = new ArrayList<>();
            var lengthTypeId = reader.read(1).get(0);

            if (lengthTypeId == '0') {
                // Type is 0 which means the next 15 bits are the total length of the subpackets in bits
                var num = Integer.parseInt(Joiner.on("").join(reader.read(15)), 2);

                // Loop until we've read the number of bits as determined by the number above
                var end = reader.position + num;
                while (reader.position < end) {
                   subpackets.add(parse(reader));
                }
            } else if (lengthTypeId == '1') {
                // Type is 1 which means the next 11 bits are the number of subpacket
                var num = Integer.parseInt(Joiner.on("").join(reader.read(11)), 2);

                // Loop parsing a subpacket the number of times determined by the number above
                for (var i = 0; i < num; i++) {
                    subpackets.add(parse(reader));
                }
            }

            return new Packet(version, type, subpackets, null);
        }
    }

    private static class BitReader {
        public int position = 0;
        private final List<Character> bits;

        public BitReader(List<Character> bits) {
            this.bits = bits;
        }

        public List<Character> read(int length) {
            var readBits = bits.subList(position, Math.min(position + length, bits.size()));
            position += length;
            return readBits;
        }
    }

    private List<Character> padZeros(List<Character> bits) {
        for (var i = 0; i < bits.size() % 4; i++) {
            bits.add(i, '0');
        }
        return bits;
    }
}