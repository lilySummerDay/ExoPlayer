/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.SeekMap;
import java.io.IOException;

/**
 * Used to seek in an Ogg stream. OggSeeker implementation may do direct seeking or progressive
 * seeking. OggSeeker works together with a {@link SeekMap} instance to capture the queried position
 * and start the seeking with an initial estimated position.
 */
/* package */ interface OggSeeker {

  /**
   * @return a SeekMap instance which returns an initial estimated position for progressive seeking
   *     or the final position for direct seeking. Returns null if {@link #read} hasn't returned -1
   *     yet.
   */
  SeekMap createSeekMap();

  /**
   * Initializes a seek operation.
   *
   * @return The granule position targeted by the seek.
   */
  long startSeek();

  /**
   * Reads data from the {@link ExtractorInput} to build the {@link SeekMap} or to continue a
   * progressive seek.
   * <p/>
   * If more data is required or if the position of the input needs to be modified then a position
   * from which data should be provided is returned. Else a negative value is returned. If a seek
   * has been completed then the value returned is -(currentGranule + 2). Else -1 is returned.
   *
   * @param input the {@link ExtractorInput} to read from.
   * @return the non-negative position to seek the {@link ExtractorInput} to or -1 seeking not
   *     necessary or at the end of seeking a negative number < -1 which is -(currentGranule + 2).
   * @throws IOException thrown if reading from the input fails.
   * @throws InterruptedException thrown if interrupted while reading from the input.
   */
  long read(ExtractorInput input) throws IOException, InterruptedException;
}
