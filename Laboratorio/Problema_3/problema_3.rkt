;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname problema_3) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
(define bin-int->rad10  ; val: integer in radix 10
  (lambda (bin)         ; bin: binary digits string
    (let ((last-bit-idx (- (string-length bin) 1)))
      ; base case
      (if (< (string-length bin) 2)
          (string->number bin)
          ; recursive step
          (+
           (* 2 (bin-int->rad10 (substring bin 0 last-bit-idx)))
           (string->number (substring bin last-bit-idx))
           )
          )
    )))

(define bin-float->rad10  ; val: number's decimal part expansion in radix 10
  (lambda (bin)           ; bin: binary digits string (decimal part)
    (let ((exp (string-length bin)))
      (let ((last-idx (- exp 1)))
        ; base case
        (if (= exp 1)
            (* 1/2 (string->number bin))
            ; recursive step
            (+
             (bin-float->rad10 (substring bin 0 last-idx))
             (* (expt 2 (- exp)) (string->number (substring bin last-idx))))
            )
        ))))

(define point-idx  ; val: non-negative integer (index of the first point found in str)
  (lambda (str)    ; str: string with a point
    ; base cases
    (if (char=? (string-ref str 0) #\.)
        0
        ; recursive step
        (add1 (point-idx (substring str 1)))
        )
    ))

(define contains-point?  ; val: bool (check wether a string contains at least a point)
  (lambda (str)          ; str: string
    ; base cases
    (cond
      ((= (string-length str) 0)
       ; whole string has been parsed
       #f
       )
      ((char=? (string-ref str 0) #\.)
       ; point is found
       #t
       )
      ; recursive step
      (else (contains-point? (substring str 1)))
      )   
     ))

(define bin-rep->module  ; val: binary number's non-negative decimal expansion in radix 10 
  (lambda (bin)          ; bin: binary digits string possibly with floating point
    ; handle floating point
    (if (contains-point? bin)
        (let ((integer-part (substring bin 0 (point-idx bin)))
              (float-part (substring bin (+ (point-idx bin) 1)))
              )
          (+
           (bin-int->rad10 integer-part)
           (bin-float->rad10 float-part)
           ))
        ; handle integer
        (bin-int->rad10 bin)
        )
    ))

(define bin-rep->number  ; val: binary number's decimal expansion in radix 10
  (lambda (bin)          ; bin: binary digits string possibly with [+, -, .] chars
    (let ((sign (string-ref bin 0))
          (mod (substring bin 1))
          )
      (cond ((char=? sign #\-)
             (- (bin-rep->module mod))
             )
            ((char=? sign #\+)
             (bin-rep->module mod)
             )
            (else
             (bin-rep->module bin)
             )
        )
    )))

;; test

(bin-rep->number "+1101")      ; 13
(bin-rep->number "0")          ; 0
(bin-rep->number "10110.011")  ; 22.375
(bin-rep->number "-0.1101001") ; -0.8203125

;; Generalization for radix n with an arbitrary digits set.
;; For exercise's sakes a different algorithm than the previous will be implemented in order to convert numerals in radix 10

(define r-digit->d-digit  ; val: digit's radix 10 valor in an arbitrary digits set in radix n
  (lambda (d-set digit)   ; d-set: string of arbitrary digits sorted in ascending order
                          ; digit: character contained in d-set
    ; base case
    (if (char=? digit (string-ref d-set 0)) ; must use char type to avoid index error if string-legth is 1
        0
        ; recursive step
        (add1
         (r-digit->d-digit
          (substring d-set 1)
          digit
          )
         )
        )
    ))

(define integer->rad10     ; val: decimal non-negative expansion of a numeral string in radix n with arbitrary digits set
  (lambda (d-set numeral)  ; d-set: string of arbitrary digits sorted in ascending order
                           ; numeral: string representation of a number in radix n with d-set's digits
    ; base case
    (if (= (string-length numeral) 1)
        (r-digit->d-digit d-set (string-ref numeral 0))
        ; recursive step
        (let ((radix (string-length d-set))
              (last-d-idx (- (string-length numeral) 1))
              )
          (let ((left-part (substring numeral 0 last-d-idx))
                (last-d (string-ref numeral last-d-idx))
                )
            (+
             (r-digit->d-digit d-set last-d)
             (* radix (integer->rad10 d-set left-part))
             )
            )
          )
        )
   ))

(define rep->module       ; val: decimal non-negative expansion of a numeral string in radix n with arbitrary digits set
  (lambda (d-set numeral) ; d-set: string of arbitrary digits sorted in ascending order
                          ; numeral: string representation of number in radix n with d-set's digits and possibly a floating point
    ; handle floating point
    (if (contains-point? numeral)
        (let ((decimal-part (substring numeral (+ (point-idx numeral) 1))))
          (let ((numeral-wo-point (string-append
                                   (substring numeral 0 (point-idx numeral))
                                   decimal-part
                                   )
                                  )
                )
            (/
             (integer->rad10 d-set numeral-wo-point)
             (integer->rad10 d-set (number->string (expt 10 (string-length decimal-part))))
             )
            ))
        ; handle integer
        (integer->rad10 d-set numeral)
        )
    ))

(define rep->number        ; val: decimal expansion of a numeral string in radix n with arbitrary digits set
  (lambda (d-set numeral)  ; d-set: string of arbitrary digits sorted in ascending order
                           ; numeral: string representation of number in radix n with d-set's digits and possibly with [+, -, .] chars
    (let ((sign (string-ref numeral 0))
          (mod (substring numeral 1))
          )
      (cond ((char=? sign #\-)
             (- (rep->module d-set mod))
             )
            ((char=? sign #\+)
             (rep->module d-set mod)
             )
            (else
             (rep->module d-set numeral)
             )
        )
    )))

;; test

(rep->number "zu" "-uuzz")               ; -12
(rep->number "0123" "+21.1")             ; 9.25
(rep->number "01234" "-10.02")           ; -5.08
(rep->number "0123456789ABCDEF" "0.A")   ; 0.625
(rep->number "0123456789ABCDEF" "1CF.0") ; 463