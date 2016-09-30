/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trksoft.cocam;

/**
 *
 * @author PSUANZES
 */
    public enum MatchScoreType {
        R04(4), R13(13), R22(22), R31(31), R40(40);

        private int matchScoreTypeNo;

        private static final java.util.Map<Integer, MatchScoreType> map
            = new java.util.HashMap<>();

        static {
            for (MatchScoreType matchScoreType : MatchScoreType.values()) {
                map.put(matchScoreType.matchScoreTypeNo, matchScoreType);
            }
        }

        private MatchScoreType(final int matchScoreTypeNo) {
            this.matchScoreTypeNo = matchScoreTypeNo;
        }

        public static MatchScoreType valueOf(final int resultTypeNo) {
            return map.get(resultTypeNo);
        }
    }